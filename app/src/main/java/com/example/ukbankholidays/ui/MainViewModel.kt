package com.example.ukbankholidays.ui

import androidx.lifecycle.ViewModel
import com.example.ukbankholidays.model.HolidayModel
import com.example.ukbankholidays.repo.Repository
import com.example.ukbankholidays.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _data: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val data: StateFlow<UIState> get() = _data

    var selected = 0
    lateinit var responseSaved: HolidayModel

    fun getData() {
        _data.value = (UIState.Loading)
        CoroutineScope(Dispatchers.Main).launch {
            val response = repository.getBankHolidays()
            if (response != null) {
                _data.value = UIState.Success(response)
                responseSaved = response
            } else {
                _data.value = (UIState.Fail(""))
            }
        }
    }
}