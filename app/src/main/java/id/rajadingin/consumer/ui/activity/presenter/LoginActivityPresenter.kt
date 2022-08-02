package id.rajadingin.consumer.ui.activity.presenter

import android.util.Log
import id.rajadingin.consumer.model.request.LoginRequest
import id.rajadingin.consumer.networking.Const
import id.rajadingin.consumer.model.responses.LoginResponse
import id.rajadingin.consumer.model.responses.RegisterResponse
import id.rajadingin.consumer.networking.ApiClient
import id.rajadingin.consumer.networking.Endpoint
import id.rajadingin.consumer.ui.activity.interfaces.LoginActivityContractInterface.Presenter
import id.rajadingin.consumer.ui.activity.interfaces.LoginActivityContractInterface.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityPresenter(_view: View) : Presenter {

    private var view: View = _view

    init { view.initView() }

    override fun loginTap(user: String, pwd: String) {
        view.hideKeyboard()
        val request = LoginRequest(user, pwd)

        ApiClient.client.create(Endpoint::class.java).UserSigningIn(request).enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
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

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                Log.i(Const.CALLBACK_RESPONSE, t.toString())

                view.showLoginToast(Const.PROMPT_SERVER_NO_RESPONSE)
            }
        })
    }


}