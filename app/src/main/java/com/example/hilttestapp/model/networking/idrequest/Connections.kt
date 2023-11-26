package com.example.hilttestapp.model.networking.idrequest


import com.google.gson.annotations.SerializedName

data class Connections(
    @SerializedName("group-affiliation")
    val groupAffiliation: String,
    @SerializedName("relatives")
    val relatives: String
)