package com.example.ukbankholidays.repo

import com.example.ukbankholidays.model.HolidayModel

interface Repository {
    suspend fun getBankHolidays(): HolidayModel
}