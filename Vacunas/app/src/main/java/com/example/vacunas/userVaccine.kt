package com.example.vacunas

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import java.util.*

data class userVaccine(
    val domicilio:String?,
    val marca:String?,
    val numeroLote:String?,
    val centroAplicacion:String?,
    val doctor:String?,
    val esquemaVacunacion:String?,
    val fechaAplicacion:Date?,
    val hasIt:Boolean?,
    val reference: DocumentReference?){

    @Exclude
    @set:Exclude
    @get:Exclude
    var uid: String? = null
    constructor(): this(null, null, null, null, null, null, null, null, null)
}