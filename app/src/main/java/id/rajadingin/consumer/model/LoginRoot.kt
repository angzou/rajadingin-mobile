package id.rajadingin.consumer.model

import com.google.gson.annotations.SerializedName

data class LoginRoot (

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("role")
    val role: String? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("msg")
    val msg: String? = null
)