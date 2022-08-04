package com.example.constantatestapp.model.datasource

interface DataSource<T> {
    suspend fun getData(): T
}