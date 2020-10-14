package com.eldisproject.kotlinmvvm.data.api

import com.eldisproject.kotlinmvvm.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {
    private val endpoint: String = "https://5e510330f2c0d300147c034c.mockapi.io/users"

    override fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get(endpoint)
            .build()
            .getObjectListSingle(User::class.java)
    }
}