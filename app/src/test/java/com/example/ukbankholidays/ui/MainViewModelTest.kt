package com.example.ukbankholidays.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.ukbankholidays.model.*
import com.example.ukbankholidays.repo.Repository
import com.example.ukbankholidays.util.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.internal.runners.statements.Fail
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class MainViewModelTest{

    val dispatcher = TestCoroutineDispatcher()
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }



    @Test
    fun `Given BankHoliday data from response When success Then result is empty`() = runBlocking {
        val response = HolidayModel(
            englandAndWales = EnglandAndWalesModel("", emptyList()),
            northernIreland = NorthernIrelandModel("", emptyList()),
            scotland = ScotlandModel("", emptyList())
        )

        whenever(repository.getBankHolidays()).thenReturn(response)
        viewModel.getData()
        viewModel.data.asLiveData().observeForever {
            assertEquals(it as UIState, response)
        }
    }


    @Test
    fun `Given Holiday data from response When success Then return all data`()= runBlocking{
        val holiday = arrayListOf<HolidayModel>(
            HolidayModel(englandAndWales = EnglandAndWalesModel("England and Wales", emptyList())
                , northernIreland = NorthernIrelandModel("Northern Island", emptyList())
                , scotland = ScotlandModel("Scotland", emptyList()))
        )

        Mockito.`when`(repository.getBankHolidays()).thenReturn(HolidayModel(
            englandAndWales = EnglandAndWalesModel("England and Wales", emptyList())
            , northernIreland = NorthernIrelandModel("Northern Island", emptyList())
            , scotland = ScotlandModel("Scotland", emptyList())
        ))
        viewModel.getData()
        viewModel.data.asLiveData().observeForever{
            assertEquals((it as UIState.Success<*>).uiResponse,holiday)
        }

    }


}