package com.eldisproject.kotlinmvvm.data.repository

import com.eldisproject.kotlinmvvm.data.api.ApiHelper
import com.eldisproject.kotlinmvvm.data.model.User
import io.reactivex.Single

class MainRepository (private val apiHelper: ApiHelper) {
    fun getUsers(): Single<List<User>> = apiHelper.getUser()
}