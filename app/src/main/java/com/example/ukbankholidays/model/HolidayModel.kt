package com.example.ukbankholidays.model


import com.google.gson.annotations.SerializedName

data class HolidayModel(
    @SerializedName("england-and-wales")
    val englandAndWales: EnglandAndWalesModel,
    @SerializedName("northern-ireland")
    val northernIreland: NorthernIrelandModel,
    @SerializedName("scotland")
    val scotland: ScotlandModel
)