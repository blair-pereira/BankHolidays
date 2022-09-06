package com.example.ukbankholidays.api

import com.example.ukbankholidays.model.HolidayModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiDetails {

    @GET("bank-holidays.json")
    suspend fun getBankHolidays(): HolidayModel
}