import androidx.lifecycle.MutableLiveData

fun <T: Any?> MutableLiveData<T>.default(initialValue: T) = apply { postValue(initialValue) }
fun <T> MutableLiveData<T>.set(newValue: T) = apply { postValue(newValue) }