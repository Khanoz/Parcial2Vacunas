package com.example.vacunas

import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object Utils {
    val missingVaccinesList: ArrayList<DocumentReference> = ArrayList()
    var selectedVaccine: DocumentReference? = null
    lateinit var vaccineNames: HashMap<DocumentReference, String>
    lateinit var vaccineReferences: HashMap<String, DocumentReference>
    val auth = FirebaseAuth.getInstance()
    lateinit var userid: String
    lateinit var personid: String

    fun getVaccineNamesFromDB(){
        vaccineNames = HashMap()
        vaccineReferences = HashMap()
        FirebaseFirestore.getInstance().collection("vaccines")
            .addSnapshotListener { value, error ->
                value!!.documents.forEach {
                    val title: String = it.get("title").toString()
                    val ref: DocumentReference = it.reference
                    val id = it.id

                    vaccineNames.put(ref, title)
                    vaccineReferences.put(id, ref)
                }
            }
    }
    fun registerUser(userdata: userData, email: String, password: String){
        val db = FirebaseFirestore.getInstance()

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            db.collection("users").document(it.user!!.uid).set(userdata)
            generateUserVaccines(db, it.user!!.uid)
        }
    }
    fun generateUserVaccines(db: FirebaseFirestore, uid: String){
        val ref: CollectionReference = db.collection("users").document(uid)
            .collection("userVaccines")

        vaccineReferences.forEach {
            val uVaccine = userVaccine(
                null, null, null,
                null, null, null,
                null, false, it.value)
            uVaccine.uid = it.value.id
            ref.document(it.value.id).set(uVaccine)
            }
        }
}