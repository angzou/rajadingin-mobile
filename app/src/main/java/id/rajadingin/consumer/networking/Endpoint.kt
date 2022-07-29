package id.rajadingin.consumer.networking

import id.rajadingin.consumer.model.*
import retrofit2.Call
import okhttp3.RequestBody
import retrofit2.http.*

interface Endpoint {

    @Headers("Content-Type: application/json")
    @POST("Login/login")
    fun SigningIn(@Body body: RequestBody): Call<LoginRoot>

    @Headers("content-type: application/json")
    @POST("Login/registerCustomer")
    fun RegisterUser(@Body body: RequestBody): Call<RegisterUser>


}

