package com.eldisproject.kotlinmvvm.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getUser() = apiService.getUsers()
}