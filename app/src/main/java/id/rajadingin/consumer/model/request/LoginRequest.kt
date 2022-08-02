package id.rajadingin.consumer.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest (

    @field:SerializedName("username")
    val status: String? = null,

    @field:SerializedName("password")
    val username: String? = null

)