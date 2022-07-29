package id.rajadingin.consumer.ui.activity.classes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.rajadingin.consumer.R
import id.rajadingin.consumer.model.Const
import id.rajadingin.consumer.databinding.ActivityLoginCardBinding
import id.rajadingin.consumer.ui.activity.interfaces.LoginActivityContractInterface
import id.rajadingin.consumer.ui.activity.presenter.LoginActivityPresenter
import id.rajadingin.consumer.utils.SharedPreference

class LoginActivity : AppCompatActivity(), LoginActivityContractInterface.View {

    private lateinit var binding: ActivityLoginCardBinding
    private var presenter: LoginActivityPresenter? = null
    private var doubleBackToExitPressedOnce = false
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        supportActionBar?.hide()
        presenter = LoginActivityPresenter(this)
        sharedPreference = SharedPreference(this)

    }

    override fun initView() {
        binding.TFUsername.addTextChangedListener(nimTextwatcher)
        binding.btnLogin.setOnClickListener{
//            Tools.showPopupProgressSpinner(this)
            emptyCheck() }
        binding.signUp.setOnClickListener{ navigateToRegisterActivity() }
    }

    override fun navigateToMainActivity() {

//        sharedPreference.save(Const.IS_LOGGED_IN, true)

        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
    }

    override fun navigateToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun showLoginToast(message: String) {
//        Tools.closePopupProgressSpinner()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    override fun emptyCheck() {
        val usernameContent = binding.TFUsername.text.toString()
        val passContent = binding.TFPassword.text.toString()

        if (usernameContent.isEmpty() || passContent.isEmpty()) {
//            Tools.closePopupProgressSpinner()
            showLoginToast(Const.PROMPT_FIELD_KOSONG)
        } else {
            presenter?.loginTap(
                usernameContent,
                passContent
            )
        }

    }

    override val nimTextwatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
//            val content = binding.TFUsername.text.toString()
//            binding.TFUsername.error = if (content.length != 13 && content.isNotEmpty()) null else "Minimal 12 digit "
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }


    }

    override fun saveNim() {
        val nimContent = binding.TFUsername.text.toString()
//        sharedPreference.save(Const.USER_NIM, nimContent)
    }

//    override fun saveUserDetail(userDetailList: UserDetail?) {
//        if (sharedPreference.setUserDetailLists(userDetailList)) {
//            Tools.closePopupProgressSpinner()
//            navigateToMainActivity()
//        }
//    }

    override fun getUserDetailListFromSharedPreference() {
//        Log.i("USER DETAIL", sharedPreference.getUserDetailLists()?.nama.toString())
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            this.finishAffinity()
            return
        }

        this.doubleBackToExitPressedOnce = true
        showLoginToast(Const.PROMPT_BACK_TWICE_TO_EXIT)

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}