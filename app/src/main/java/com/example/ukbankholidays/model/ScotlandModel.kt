package com.example.ukbankholidays.model


import com.google.gson.annotations.SerializedName

data class ScotlandModel(
    @SerializedName("division")
    val division: String,
    @SerializedName("events")
    val events: List<EventModel>
)