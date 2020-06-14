package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoomUserViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}