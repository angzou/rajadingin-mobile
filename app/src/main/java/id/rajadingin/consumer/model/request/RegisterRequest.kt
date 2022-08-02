package id.rajadingin.consumer.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (

    @field:SerializedName("email")
    val usersEmail: String? = null,

    @field:SerializedName("username")
    val usersUsername: String? = null,

    @field:SerializedName("phone")
    val usersPhoneNumber: String? = null,

    @field:SerializedName("address")
    val usersAddress: String? = null,

    @field:SerializedName("provinsi")
    val usersProvince: String? = null,

    @field:SerializedName("kabupaten")
    val usersKab: String? = null,

    @field:SerializedName("kecamatan")
    val usersKec: String? = null,

    @field:SerializedName("kelurahan")
    val usersKel: String? = null,

    @field:SerializedName("postal_code")
    val usersPostalCode: String? = null,

    @field:SerializedName("password")
    val usersPwd: String? = null,

    @field:SerializedName("longitude")
    val usersLong: String? = null,

    @field:SerializedName("latitude")
    val usersLat: String? = null,

    @field:SerializedName("userName")
    val usersFullName: String? = null

)