package id.rajadingin.consumer.ui.activity.classes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.rajadingin.consumer.databinding.ActivitySplashscreenBinding
import id.rajadingin.consumer.ui.activity.interfaces.SplashScreenContractInterface
import id.rajadingin.consumer.ui.activity.presenter.SplashScreenPresenter
import id.rajadingin.consumer.utils.SharedPreference
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity(), SplashScreenContractInterface.View {

    private lateinit var binding: ActivitySplashscreenBinding
    private var presenter: SplashScreenPresenter? = null
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        presenter = SplashScreenPresenter(this)
        sharedPreference = SharedPreference(this)
        actionBar?.hide()

        Timer().schedule(1500){
//            if (sharedPreference.getValueBoolean(Const.IS_LOGGED_IN)) {
//                goToMainMenu()
//            } else {
                goToLogin()
//            }
        }



    }

    override fun initView() {


    }

    override fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun goToMainMenu() {
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
    }
}