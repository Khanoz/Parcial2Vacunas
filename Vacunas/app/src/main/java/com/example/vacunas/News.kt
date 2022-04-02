package com.example.vacunas

import com.google.firebase.firestore.Exclude
import java.util.*
import kotlin.collections.ArrayList

data class News(
    val title:String?,
    val date: Date?,
    val description:String?,
    val image: String? = null,
    val link: String? = null){
    @Exclude
    @set:Exclude
    @get:Exclude
    var uid: String? = null
    constructor(): this(null,null,null,null,null)
}