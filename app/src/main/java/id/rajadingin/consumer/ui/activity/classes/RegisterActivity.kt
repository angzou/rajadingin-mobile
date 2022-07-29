package id.rajadingin.consumer.ui.activity.classes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.rajadingin.consumer.R
import id.rajadingin.consumer.databinding.ActivityRegisterBinding
import id.rajadingin.consumer.databinding.IncludeRegisterFormFieldBinding
import id.rajadingin.consumer.ui.activity.interfaces.RegisterActivityContractInterface
import id.rajadingin.consumer.ui.activity.presenter.RegisterActivityPresenter
import id.rajadingin.consumer.utils.SharedPreference

class RegisterActivity : AppCompatActivity(), RegisterActivityContractInterface.View {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var formBinding: IncludeRegisterFormFieldBinding
    private var presenter: RegisterActivityPresenter? = null
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        formBinding = binding.registrationFormField
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.register_bk_color)
        presenter = RegisterActivityPresenter(this)
        sharedPreference = SharedPreference(this)
    }

    override fun initView() {



    }




}