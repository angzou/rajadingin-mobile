package id.rajadingin.consumer.ui.fragment.classes.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoriesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Categories Fragment"
    }
    val text: LiveData<String> = _text
}