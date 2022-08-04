package com.example.constantatestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.constantatestapp.model.data.AppState
import com.example.constantatestapp.model.data.DataModel
import com.example.constantatestapp.model.repository.Repository
import com.example.constantatestapp.model.repository.RepositoryImpl
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository<DataModel> = RepositoryImpl()
) : ViewModel() {

    private val _liveData: MutableLiveData<AppState> = MutableLiveData()

    val liveDataToObserve: LiveData<AppState> = _liveData

    init {
        viewModelScope.launch {
            _liveData.postValue(AppState.Loading)
            try {
                val responseData = repository.getData()
                _liveData.postValue(AppState.Success(responseData.orderItems()))
            } catch (e: Throwable) {
                _liveData.postValue(AppState.Error(e))
            }
        }
    }
}