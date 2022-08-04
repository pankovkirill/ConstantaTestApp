package com.example.constantatestapp.model.repository

import com.example.constantatestapp.model.data.DataModel
import com.example.constantatestapp.model.datasource.DataSource
import com.example.constantatestapp.model.datasource.RetrofitImpl

class RepositoryImpl(
    private val dataSource: DataSource<DataModel> = RetrofitImpl()
) : Repository<DataModel> {
    override suspend fun getData(): DataModel {
        return dataSource.getData()
    }
}