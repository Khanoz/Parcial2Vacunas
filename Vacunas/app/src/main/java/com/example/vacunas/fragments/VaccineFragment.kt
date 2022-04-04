package com.example.vacunas.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vacunas.*
import com.example.vacunas.activities.AddVaccineActivity
import com.example.vacunas.adapters.MyVaccineAdapter
import com.example.vacunas.databinding.FragmentVaccineBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList

class VaccineFragment : Fragment() {

    private var _binding: FragmentVaccineBinding? = null
    private var adapter: MyVaccineAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVaccineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun CreateAdapter(list: ArrayList<Vaccine> = ArrayList()){

        val recyclerView: RecyclerView? = getView()?.findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        adapter = MyVaccineAdapter(list, this.context)
        recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().findViewById<ImageButton>(R.id.add_vaccine)!!.setOnClickListener {
            val intent = Intent(activity, AddVaccineActivity::class.java)
            startActivity(intent)
        }

        val db = FirebaseFirestore.getInstance()

        val list: ArrayList<Vaccine> = ArrayList()
        db.collection("users").document(Utils.userid)
            .collection("userVaccines").addSnapshotListener { value, error ->
                value!!.documents.forEachIndexed { index, reference ->
                    if(reference.get("hasIt") as Boolean){
                        val ref: DocumentReference = reference.get("reference") as DocumentReference
                        val titulo = Utils.vaccineNames[ref]
                        val vacc = Vaccine(titulo)

                        vacc.uid = reference.id
                        list.add(vacc)
                    }
                    if(index+1 >= value!!.documents.size){
                        CreateAdapter(list)
                    }
                }
            }

        /*db.collection("users").document("user1")
            .addSnapshotListener { value, error ->
                val ref: ArrayList<DocumentReference> = value!!
                    .get("userVaccines") as ArrayList<DocumentReference>
                val list: MutableList<Vaccine> = mutableListOf()
                Log.d("holaaa", ref.toString())

                ref.forEach {
                    it.addSnapshotListener { value, error ->
                        Log.d("asdasd", value!!.id.toString())
                        val vaccine = value!!.toObject(Vaccine::class.java)!!
                        list.add(vaccine)
                        if(list.size >= ref.size){
                            val adapter = MyVaccineAdapter(list)
                            recyclerView.adapter = adapter
                        }
                    }
                }
            }*/
        /*db.collection("users").addSnapshotListener { value, error ->
            val news = value!!.toObjects(News::class.java)

            news.forEachIndexed { index, publicacion ->
                publicacion.uid = value.documents[index].id
            }
            val adapter = MyAdapter(news, this.requireContext())
            recyclerView.adapter = adapter
        }*/
    }

}