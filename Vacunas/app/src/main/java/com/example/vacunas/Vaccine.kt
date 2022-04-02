package com.example.vacunas

import com.google.firebase.firestore.Exclude
import java.util.*

data class Vaccine(
    val title:String?){
    @Exclude
    @set:Exclude
    @get:Exclude
    var uid: String? = null
    constructor(): this(null)
}