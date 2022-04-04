package com.example.vacunas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import com.example.vacunas.R
import com.example.vacunas.Utils
import com.example.vacunas.userVaccine
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import java.text.SimpleDateFormat

class ViewVaccineInfoActivity : AppCompatActivity() {
    private lateinit var docUid: String
    private val db = FirebaseFirestore.getInstance()
    private lateinit var listener: ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_vaccine_info)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.title = ""

        docUid = intent.extras!!.getString("uid")!!

        findViewById<ImageButton>(R.id.newvacc_main_close).setOnClickListener {
            this.finish()
        }
        listener = db.collection("users").document(Utils.userid)
                    .collection("userVaccines").document(docUid)
                    .addSnapshotListener { value, error ->
                        val userVaccine = value!!.toObject(userVaccine::class.java)

                        findViewById<TextView>(R.id.selected_vaccine).text = docUid
                        findViewById<TextView>(R.id.esquema).text = userVaccine!!.esquemaVacunacion
                        val format: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                        findViewById<TextView>(R.id.fecha).text = format.format(userVaccine.fechaAplicacion!!)
                        findViewById<TextView>(R.id.marca).text = userVaccine.marca
                        findViewById<TextView>(R.id.lote).text = userVaccine.numeroLote
                        findViewById<TextView>(R.id.doctor).text = userVaccine.doctor
                        findViewById<TextView>(R.id.centroAplicacion).text = userVaccine.centroAplicacion
                        findViewById<TextView>(R.id.domicilio).text = userVaccine.domicilio
                    }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.dosis_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.new_dosis -> {
                listener.remove()
                Utils.selectedVaccine = Utils.vaccineReferences[docUid]
                val newintent = Intent(this, NuevaDosisActivity::class.java)
                startActivity(newintent)
                this.finish()
                return true
            }
            R.id.edit_dosis -> {
                listener.remove()
                Utils.selectedVaccine = Utils.vaccineReferences[docUid]
                val newintent = Intent(this, NuevaDosisActivity::class.java)
                startActivity(newintent)
                this.finish()
                return true
            }
            R.id.delete_dosis -> {
                listener.remove()
                val newUserVaccine = userVaccine(null, null, null,
                    null, null, null,
                    null, false, Utils.vaccineReferences[docUid])
                db.collection("users").document(Utils.userid)
                    .collection("userVaccines").document(docUid).set(newUserVaccine)
                this.finish()
                return true
            }
            else -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}