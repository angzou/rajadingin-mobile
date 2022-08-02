package id.rajadingin.consumer.model.request

import com.google.gson.annotations.SerializedName

data class KelRequest (

    @field:SerializedName("provinsi")
    val prov: String? = null,

    @field:SerializedName("kabupaten")
    val kab: String? = null,

    @field:SerializedName("kecamatan")
    val kec: String? = null

)