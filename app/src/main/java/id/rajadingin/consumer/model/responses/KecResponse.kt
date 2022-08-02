package id.rajadingin.consumer.model.responses

import com.google.gson.annotations.SerializedName

data class KecResponse (

    @field:SerializedName("provinsi")
    val prov: String? = null,

    @field:SerializedName("kab_kota")
    val kab: String? = null,

    @field:SerializedName("kecamatan")
    val kec: String? = null

)