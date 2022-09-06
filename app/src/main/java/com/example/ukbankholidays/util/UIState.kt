package com.example.ukbankholidays.util

sealed class UIState {
    object Loading:UIState()
    class Success<T>(val uiResponse :T):UIState()
    class Fail(val error :String):UIState()
}