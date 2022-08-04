package com.example.constantatestapp.model.data.api

import com.example.constantatestapp.model.data.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("/constanta-android-dev/intership-wellcome-task/main/films.json")
    fun getDataAsync(): Deferred<DataModel>
}