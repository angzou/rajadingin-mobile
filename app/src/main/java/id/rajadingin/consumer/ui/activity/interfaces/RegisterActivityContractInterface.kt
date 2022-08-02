package id.rajadingin.consumer.ui.activity.interfaces

import android.text.TextWatcher
import id.rajadingin.consumer.model.responses.KecResponse
import id.rajadingin.consumer.model.responses.KelResponse

interface RegisterActivityContractInterface {

    interface View {
        fun initView()
        fun initKecSpinner(listKecamatan: List<KecResponse>)
        fun initKelSpinner(listKelurahan: List<KelResponse>)
        fun navigateToMapsActivity()
        fun showRegisterToast(message: String)
        val pwdTextwatcher: TextWatcher
    }

    interface Presenter {
        fun regis(
            userFullName: String,
            userEmail: String,
            userUsername: String,
            userPhone: String,
            userAddress: String,
            userProvince: String,
            userKab: String,
            userKec: String,
            userKel: String,
            userPosCode: String,
            userPwd: String,
            userLong: String,
            userLat: String
        )
        fun loadKecamatan(prov: String, kab: String)
        fun loadKelurahan(prov: String, kab: String, kec: String)

    }

}