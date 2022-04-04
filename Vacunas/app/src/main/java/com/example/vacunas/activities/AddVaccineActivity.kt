package com.example.vacunas.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vacunas.*
import com.example.vacunas.adapters.MyAddVaccineAdapter
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class AddVaccineActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val userVaccines: ArrayList<DocumentReference> = ArrayList()
    private val dbListeners: ArrayList<ListenerRegistration> = ArrayList()
    private lateinit var userReference: DocumentReference

    override fun onDestroy() {
        super.onDestroy()

        dbListeners.forEachIndexed { index, listenerRegistration ->
            listenerRegistration.remove()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vaccine)

        SetupDataForAdapter()

        findViewById<ImageButton>(R.id.addvac_close).setOnClickListener {
            this.finish()
        }
        findViewById<ImageButton>(R.id.addvac_save).setOnClickListener {
            if(Utils.selectedVaccine != null) {
                addUserVaccines()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun SetupDataForAdapter(){
        Utils.missingVaccinesList.clear()
        dbListeners.add(
            db.collection("users").document(Utils.userid)
                .collection("userVaccines").addSnapshotListener { value, error ->
                    userVaccines.clear()
                    val documents = value!!.documents
                    documents.forEachIndexed { index, documentSnapshot ->
                        if(documentSnapshot.get("hasIt") as Boolean){
                            userVaccines.add(documentSnapshot
                                .get("reference") as DocumentReference)
                        }
                        if(index+1 >= documents.size){
                            checkForMissingVaccines()
                        }
                    }
                }
                /*.addSnapshotListener { value, error ->
                    userVaccines.clear()
                    userVaccines.addAll(value!!
                        .get("userVaccines") as ArrayList<DocumentReference>)
                    checkForMissingVaccines()
                }*/
        )
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkForMissingVaccines(){
        Utils.missingVaccinesList.clear()
        Utils.vaccineNames.forEach { reference, s ->
            if(!userVaccines.contains(reference)){
                Utils.missingVaccinesList.add(reference)
            }
        }
        addMissingVaccinesToAdapter()
    /*db.collection("vaccines").addSnapshotListener { value, error ->
                value!!.documents.forEach {

                    Utils.missingVaccinesList.add(it.reference)
                    if(Utils.missingVaccinesList.size >= value!!.documents.size){
                        if(!userVaccines.containsAll(Utils.missingVaccinesList)){
                            Utils.missingVaccinesList.removeAll(userVaccines)
                            addMissingVaccinesToAdapter()
                        }
                    }
                }
            }*/
    }
    private fun addMissingVaccinesToAdapter(){
//        db.collection("users").document("user1").update("userVaccines", list)

        val vaccineList: MutableList<Vaccine> = mutableListOf()
        Utils.missingVaccinesList.forEach {
            val title: String? = Utils.vaccineNames[it]
            vaccineList.add(Vaccine(title))
        }
        CreateRecycleViewAdapter(vaccineList)
        /*val vaccineList: MutableList<Vaccine> = mutableListOf()
        Utils.missingVaccinesList.forEach {
            dbListeners.add(
                it.addSnapshotListener { value, error ->
                    val vaccine = Vaccine(value!!.get("title").toString())
                    vaccineList.add(vaccine)
                    if(vaccineList.size >= Utils.missingVaccinesList.size){
                        CreateRecycleViewAdapter(vaccineList)
                    }
                }
            )
        }*/
    }
    private fun CreateRecycleViewAdapter(list: MutableList<Vaccine>){
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val adapter = MyAddVaccineAdapter(list, this)
        recyclerView.adapter = adapter

    }

    private fun addUserVaccines(){
        val intent = Intent(this, NuevaDosisActivity::class.java)
        startActivity(intent)
        this.finish()


        /*Utils.selectedVaccineToAdd!!.addSnapshotListener { value, error ->
            userVaccines.add(Utils.selectedVaccineToAdd!!)
            db.collection("users").document("user1")
                .update("userVaccines", userVaccines)
            Utils.selectedVaccineToAdd = null
            val intent = Intent(this, NewVaccineActivity::class.java)
            //intent.putExtra()
            //startActivity(intent)
            this.finish()
        }*/
    }


}