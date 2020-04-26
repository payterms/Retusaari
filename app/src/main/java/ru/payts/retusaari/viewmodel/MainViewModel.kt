package ru.payts.retusaari.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    private val viewStateLiveData = MutableLiveData<String>()

    init {// основной конструктор
        viewStateLiveData.value = "Hello from ViewModel"
    }

    fun viewState(): LiveData<String> = viewStateLiveData
}