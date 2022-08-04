package com.example.constantatestapp.model.repository

interface Repository<T> {
    suspend fun getData(): T
}