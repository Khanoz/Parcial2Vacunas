package com.example.vacunas

import com.google.firebase.firestore.Exclude
import java.util.*

data class userData(
    val name:String?,
    val lastname_F: String?,
    val lastname_M:String?,
    val gender: String?,
    val birthdate: Date?,
    val birth_weight: String?,
    val edad_gestal:String?,
    val blood_type: String?
    ){
    @Exclude
    @set:Exclude
    @get:Exclude
    var uid: String? = null
    constructor(): this(null,null,null,null,null,null,null,null)
}