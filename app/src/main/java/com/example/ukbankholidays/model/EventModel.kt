package com.example.ukbankholidays.model


import com.google.gson.annotations.SerializedName

data class EventModel(
    @SerializedName("bunting")
    val bunting: Boolean,
    @SerializedName("date")
    val date: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("title")
    val title: String
)