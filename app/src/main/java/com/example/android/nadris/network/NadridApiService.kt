package com.example.android.nadris.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface NadrisAPIService{

    @POST("api/Auth/Token")
    suspend fun login( @Body loginAccountModel: LoginAccountModel): Response<AuthModel>  // send email and password and receive token as return string

    @POST("api/Auth/Register")
    suspend fun createAccount(@Body accountData: CreateAccountData ):Response<AuthModel>

}
