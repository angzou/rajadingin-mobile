package id.rajadingin.consumer.ui.activity.presenter

import android.util.Log
import id.rajadingin.consumer.model.Const
import id.rajadingin.consumer.model.LoginRoot
import id.rajadingin.consumer.networking.ApiClient
import id.rajadingin.consumer.networking.Endpoint
import id.rajadingin.consumer.ui.activity.interfaces.LoginActivityContractInterface.Presenter
import id.rajadingin.consumer.ui.activity.interfaces.LoginActivityContractInterface.View
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityPresenter(_view: View) : Presenter, Callback<LoginRoot> {

    private var view: View = _view

    init { view.initView() }

    override fun loginTap(user: String, pwd: String) {
        view.hideKeyboard()

        val paramObject = JSONObject()
        paramObject.put("username", user)
        paramObject.put("password", pwd)

        val bodyRequest = paramObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
        ApiClient.client.create(Endpoint::class.java).SigningIn(bodyRequest).enqueue(this)

    }

    override fun onResponse(call: Call<LoginRoot>, response: Response<LoginRoot>) {
        val results = response.body()
        val responseCode = response.code()

        Log.v("HTTP STATUS :: ", responseCode.toString())

        when (responseCode.toString()) {
            Const.RESPONSE_204 -> {
                view.showLoginToast(Const.PROMPT_WRONGUSERNAME_WRONGPASSWORD)
            }
            Const.RESPONSE_405 -> {
                view.showLoginToast(Const.PROMPT_WRONGUSERNAME_WRONGPASSWORD)
            }
            Const.RESPONSE_200 -> {
                view.showLoginToast(Const.PROMPT_WELCOME)
                view.navigateToMainActivity()
                Log.v("CALL STATUS :: ", results?.status.toString())
                Log.v("USERNAME :: ", results?.username.toString())
                Log.v("ROLE :: ", results?.role.toString())
                Log.v("TOKEN :: ", results?.token.toString())
            }
        }

    }

    override fun onFailure(call: Call<LoginRoot>, t: Throwable) {
        Log.i(Const.CALLBACK_RESPONSE, t.toString())

        view.showLoginToast(Const.PROMPT_SERVER_NO_RESPONSE)

    }

}