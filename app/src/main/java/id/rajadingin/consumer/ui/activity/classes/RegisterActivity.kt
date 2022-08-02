package id.rajadingin.consumer.ui.activity.classes

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.innfinity.permissionflow.lib.requestEachPermissions
import com.innfinity.permissionflow.lib.requestPermissions
import id.rajadingin.consumer.R
import id.rajadingin.consumer.databinding.ActivityRegisterBinding
import id.rajadingin.consumer.databinding.IncludeRegisterFormFieldBinding
import id.rajadingin.consumer.model.responses.KecResponse
import id.rajadingin.consumer.model.responses.KelResponse
import id.rajadingin.consumer.ui.activity.interfaces.RegisterActivityContractInterface
import id.rajadingin.consumer.ui.activity.presenter.RegisterActivityPresenter
import id.rajadingin.consumer.utils.SharedPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        initProvinceSpinner()
        binding.registrationFormField.btnMap.setOnClickListener{

            CoroutineScope(Dispatchers.Main).launch {
                // just call requestPermission and pass in all required permissions
                requestPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                    .collect { permissions ->
                        // here you get the result of the requests, permissions holds a list of Permission requests and you can check if all of them have been granted:
                        val allGranted = permissions.find { !it.isGranted } == null
                        // or iterate over the permissions and check them one by one
                        permissions.forEach {
                            val granted = it.isGranted
                            navigateToMapsActivity()
                            // ...
                        }
                    }
            }


        }
    }

    private fun initProvinceSpinner() {
        val provinceSpinner = binding.registrationFormField.spinnerProvince
        val provinceStrings = resources.getStringArray(R.array.province)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, provinceStrings)
        provinceSpinner.adapter = adapter

        provinceSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                initKabSpinner(position, provinceSpinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }

    private fun initKabSpinner(prov: Int, textProv: String) {
        val provinceSpinner = binding.registrationFormField.spinnerProvince
        val kabSpinner = binding.registrationFormField.spinnerKabupaten
        when (prov) {
            0 -> {
                val kabJakStrings = resources.getStringArray(R.array.kabkotaJakarta)
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, kabJakStrings)
                kabSpinner.adapter = adapter
            }
            1 -> {
                val kabJabarStrings = resources.getStringArray(R.array.kabkotaJabar)
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, kabJabarStrings)
                kabSpinner.adapter = adapter
            }
            2 -> {
                val kabBantenStrings = resources.getStringArray(R.array.kabkotaBanten)
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, kabBantenStrings)
                kabSpinner.adapter = adapter
            }
            3 -> {
                val kabYogyaStrings = resources.getStringArray(R.array.kabkotaYogya)
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, kabYogyaStrings)
                kabSpinner.adapter = adapter
            }
            4 -> {
                val kabJatengStrings = resources.getStringArray(R.array.kabkotaJateng)
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, kabJatengStrings)
                kabSpinner.adapter = adapter
            }
        }
        kabSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                presenter?.loadKecamatan(
                    provinceSpinner.selectedItem.toString(),
                    kabSpinner.selectedItem.toString()
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

    override fun initKecSpinner(listKecamatan: List<KecResponse>) {
        val provinceSpinner = binding.registrationFormField.spinnerProvince
        val kabSpinner = binding.registrationFormField.spinnerKabupaten
        val originalList: List<KecResponse> = listKecamatan
        val kecArrays: List<String> = originalList.map { it.kec.toString() }
        val kecSpinner = binding.registrationFormField.spinnerKecamatan
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, kecArrays)
        kecSpinner.adapter = adapter
        kecSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                presenter?.loadKelurahan(
                    provinceSpinner.selectedItem.toString(),
                    kabSpinner.selectedItem.toString(),
                    kecSpinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
//                parent.setSelection()
            }
        }
    }

    override fun initKelSpinner(listKelurahan: List<KelResponse>) {
        val originalList: List<KelResponse> = listKelurahan
        val kelArrays: List<String> = originalList.map { it.kel.toString() }
        val kelSpinner = binding.registrationFormField.spinnerKelurahan
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, kelArrays)
        kelSpinner.adapter = adapter
        kelSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    override fun navigateToMapsActivity() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

}

