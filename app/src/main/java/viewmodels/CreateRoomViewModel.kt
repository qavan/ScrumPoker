package viewmodels

import androidx.lifecycle.MutableLiveData
import default
import kotlinx.coroutines.*
import set

sealed class CreateState {
    class Default: CreateState()
    class CreateSendingState: CreateState()
    class CreateSuccessState: CreateState()
    class ErrorState(val errCode:Int): CreateState()
}
class CreateRoomViewModel {
    val state: MutableLiveData<CreateState> = MutableLiveData<CreateState>().default(initialValue = CreateState.Default())

    fun create(name:String,password:String) {
        val stateMachine = CoroutineScope(Dispatchers.IO).async {
            state.set(CreateState.CreateSendingState())
            val errorMessage:String = CreateRoomImpl().createAsync(name,password).await()
            if (errorMessage.isEmpty()) launch(Dispatchers.Main) {
                state.set(CreateState.CreateSuccessState())
            }
            else launch(Dispatchers.Main) {
                state.set(CreateState.ErrorState(1))
            }
        }
    }
}

interface CreateRoomInterface {
    suspend fun createAsync(name:String,password:String):Deferred<String>
}

class CreateRoomImpl: CreateRoomInterface {
    override suspend fun createAsync(name: String, password: String): Deferred<String> {
        return GlobalScope.async {
            delay(3000)
            ""
        }
    }

}