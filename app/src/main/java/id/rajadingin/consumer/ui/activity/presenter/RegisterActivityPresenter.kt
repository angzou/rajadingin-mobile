package id.rajadingin.consumer.ui.activity.presenter

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import id.rajadingin.consumer.model.request.KecRequest
import id.rajadingin.consumer.model.request.KelRequest
import id.rajadingin.consumer.model.request.RegisterRequest
import id.rajadingin.consumer.model.responses.KecResponse
import id.rajadingin.consumer.model.responses.KelResponse
import id.rajadingin.consumer.model.responses.RegisterResponse
import id.rajadingin.consumer.networking.ApiClient
import id.rajadingin.consumer.networking.Const
import id.rajadingin.consumer.networking.Endpoint
import id.rajadingin.consumer.ui.activity.classes.MapsActivity
import id.rajadingin.consumer.ui.activity.classes.RegisterActivity
import id.rajadingin.consumer.ui.activity.interfaces.RegisterActivityContractInterface.Presenter
import id.rajadingin.consumer.ui.activity.interfaces.RegisterActivityContractInterface.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivityPresenter(_view: View) : Presenter {

    private var view: View = _view

    init { view.initView() }

    override fun regis(
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
    ) {
//        view.hideKeyboard()

        val request = RegisterRequest(userEmail,
            userUsername,
            userPhone,
            userAddress,
            userProvince,
            userKab,
            userKec,
            userKel,
            userPosCode,
            userPwd,
            userLong,
            userLat,
            userFullName)

        ApiClient.client.create(Endpoint::class.java).UserRegister(request).enqueue(object : Callback<RegisterResponse?> {
            override fun onResponse(call: Call<RegisterResponse?>, response: Response<RegisterResponse?>) {
                val results = response.body()
                val responseCode = response.code()

                Log.v("HTTP STATUS :: ", responseCode.toString())

                when (responseCode.toString()) {
                    Const.RESPONSE_201 -> {
//                view.showLoginToast(Const.PROMPT_WRONGUSERNAME_WRONGPASSWORD)
                    }
                    Const.RESPONSE_204 -> {
//                view.showLoginToast(Const.PROMPT_WRONGUSERNAME_WRONGPASSWORD)
                    }
                    Const.RESPONSE_405 -> {
//                view.showLoginToast(Const.PROMPT_WRONGUSERNAME_WRONGPASSWORD)
                    }
                    Const.RESPONSE_200 -> {
//                view.showLoginToast(Const.PROMPT_WELCOME)
//                Log.v("CALL STATUS :: ", results?.status.toString())
//                Log.v("USERNAME :: ", results?.username.toString())
//                Log.v("ROLE :: ", results?.role.toString())
//                Log.v("TOKEN :: ", results?.token.toString())
                    }
                }

            }

            override fun onFailure(call: Call<RegisterResponse?>, t: Throwable) {
                Log.e("FAIL", "Failure : $t")
            }
        })
    }

    override fun loadKecamatan(prov: String, kab: String) {
        val request = KecRequest(prov, kab)
        ApiClient.client.create(Endpoint::class.java).GetKecamatan(request).enqueue(object : Callback<List<KecResponse>?> {
                override fun onResponse(call: Call<List<KecResponse>?>, response: Response<List<KecResponse>?>) {
                    val results = response.body()
                    val responseCode = response.code()

                    Log.v("HTTP STATUS :: ", responseCode.toString())


                    when (responseCode.toString()) {
                        Const.RESPONSE_201 -> {
                        }
                        Const.RESPONSE_204 -> {
                        }
                        Const.RESPONSE_405 -> {
                        }
                        Const.RESPONSE_200 -> {
                            Log.v("HTTP STATUS :: ", results.toString())
                            if (results != null) {
                                view.initKecSpinner(results)
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<List<KecResponse>?>, t: Throwable) {
                Log.e("FAIL", "Failure : $t")

                }


        })
    }

    override fun loadKelurahan(prov: String, kab: String, kec: String) {

        val request = KelRequest(prov, kab, kec)
        ApiClient.client.create(Endpoint::class.java).GetKelurahan(request).enqueue(object : Callback<List<KelResponse>?> {
            override fun onResponse(call: Call<List<KelResponse>?>, response: Response<List<KelResponse>?>) {
                val results = response.body()
                val responseCode = response.code()

                Log.v("HTTP STATUS :: ", responseCode.toString())


                when (responseCode.toString()) {
                    Const.RESPONSE_201 -> {
                    }
                    Const.RESPONSE_204 -> {
                    }
                    Const.RESPONSE_405 -> {
                    }
                    Const.RESPONSE_200 -> {
                        Log.v("HTTP STATUS :: ", results.toString())
                        if (results != null) {
                            view.initKelSpinner(results)
                        }
                    }
                }

            }

            override fun onFailure(call: Call<List<KelResponse>?>, t: Throwable) {
                Log.e("FAIL", "Failure : $t")

            }


        })

    }

}


