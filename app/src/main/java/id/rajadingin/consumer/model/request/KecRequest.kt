package id.rajadingin.consumer.model.request

import com.google.gson.annotations.SerializedName

data class KecRequest (

    @field:SerializedName("provinsi")
    val prov: String? = null,

    @field:SerializedName("kabupaten")
    val kab: String? = null

)