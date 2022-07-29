package id.rajadingin.consumer.ui.activity.presenter

import android.util.Log
import id.rajadingin.consumer.model.Const
import id.rajadingin.consumer.model.RegisterUser
import id.rajadingin.consumer.networking.ApiClient
import id.rajadingin.consumer.networking.Endpoint
import id.rajadingin.consumer.ui.activity.interfaces.RegisterActivityContractInterface.Presenter
import id.rajadingin.consumer.ui.activity.interfaces.RegisterActivityContractInterface.View
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivityPresenter(_view: View) : Presenter, Callback<RegisterUser> {

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

        val paramObject = JSONObject()
        paramObject.put("userName", userFullName)
        paramObject.put("email", userEmail)
        paramObject.put("username", userUsername)
        paramObject.put("phone", userPhone)
        paramObject.put("address", userAddress)
        paramObject.put("provinsi", userProvince)
        paramObject.put("kabupaten", userKab)
        paramObject.put("kecamatan", userKec)
        paramObject.put("kelurahan", userKel)
        paramObject.put("postal_code", userPosCode)
        paramObject.put("password", userPwd)
        paramObject.put("longitude", userLong)
        paramObject.put("latitude", userLat)

        val bodyRequest = paramObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
        ApiClient.client.create(Endpoint::class.java).RegisterUser(bodyRequest).enqueue(this)

    }

    override fun onResponse(call: Call<RegisterUser>, response: Response<RegisterUser>) {
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

    override fun onFailure(call: Call<RegisterUser>, t: Throwable) {
        Log.i(Const.CALLBACK_RESPONSE, t.toString())

//        view.showLoginToast(Const.PROMPT_SERVER_NO_RESPONSE)

    }

}