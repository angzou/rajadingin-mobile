package id.rajadingin.consumer.networking

import id.rajadingin.consumer.model.request.KecRequest
import id.rajadingin.consumer.model.request.KelRequest
import id.rajadingin.consumer.model.request.LoginRequest
import id.rajadingin.consumer.model.request.RegisterRequest
import id.rajadingin.consumer.model.responses.*
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {

    @Headers("Content-Type: application/json")
    @POST("Login/login")
    fun UserSigningIn(@Body body: LoginRequest): Call<LoginResponse>

    @Headers("content-type: application/json")
    @POST("Login/registerCustomer")
    fun UserRegister(@Body body: RegisterRequest): Call<RegisterResponse>

    @Headers("content-type: application/json")
    @POST("Login/getKecamatan")
    fun GetKecamatan(@Body body: KecRequest): Call<List<KecResponse>>

    @Headers("content-type: application/json")
    @POST("Login/getKelurahan")
    fun GetKelurahan(@Body body: KelRequest): Call<List<KelResponse>>

}

