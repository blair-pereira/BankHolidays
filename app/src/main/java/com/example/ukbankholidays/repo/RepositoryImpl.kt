package com.example.ukbankholidays.repo

import com.example.ukbankholidays.api.ApiDetails
import com.example.ukbankholidays.model.HolidayModel
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiDetails: ApiDetails):Repository {

    override suspend fun getBankHolidays(): HolidayModel {
        return apiDetails.getBankHolidays()
    }
}