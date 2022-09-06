package com.example.ukbankholidays.model


import com.google.gson.annotations.SerializedName

data class EnglandAndWalesModel(
    @SerializedName("division")
    val division: String,
    @SerializedName("events")
    val events: List<EventModel>
)