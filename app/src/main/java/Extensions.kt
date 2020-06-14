import android.view.View
import androidx.lifecycle.MutableLiveData

fun <T: Any?> MutableLiveData<T>.default(initialValue: T) = apply { postValue(initialValue) }
fun <T> MutableLiveData<T>.set(newValue: T) = apply { postValue(newValue) }
fun View.disable() {this.isEnabled = false}
fun View.enable() {this.isEnabled = true}