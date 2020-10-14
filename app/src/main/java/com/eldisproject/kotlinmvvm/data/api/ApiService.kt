package com.eldisproject.kotlinmvvm.data.api

import com.eldisproject.kotlinmvvm.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>
}