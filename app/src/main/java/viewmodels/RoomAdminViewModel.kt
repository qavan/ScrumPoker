package viewmodels

import androidx.lifecycle.MutableLiveData
import default
import kotlinx.coroutines.*
import set

sealed class AdminState {
    class Default:AdminState()
    class VoteSomeActionState:AdminState()
    class VoteSomeActionSuccess:AdminState()
    class ErrorState(val errCode:Int):AdminState()
}

class RoomAdminViewModel {
    val state: MutableLiveData<AdminState> = MutableLiveData<AdminState>().default(initialValue = AdminState.Default())

    fun someAction() {
        val stateMachine = CoroutineScope(Dispatchers.IO).async {
            state.set(AdminState.VoteSomeActionState())
            val errorMessage = RoomAdminImpl().actionAsync().await()
            if (errorMessage.isEmpty()) launch(Dispatchers.Main) {
                state.set(AdminState.VoteSomeActionSuccess())
            }
            else launch(Dispatchers.Main) {
                state.set(AdminState.ErrorState(1))
            }
        }
    }
    fun someAnotherAction() {
        val stateMachine = CoroutineScope(Dispatchers.IO).async {
            state.set(AdminState.VoteSomeActionState())
            val errorMessage = RoomAdminImpl().anotherActionAsync().await()
            if (errorMessage.isEmpty()) launch(Dispatchers.Main) {
                state.set(AdminState.VoteSomeActionSuccess())
            }
            else launch(Dispatchers.Main) {
                state.set(AdminState.ErrorState(1))
            }
        }
    }
}

interface RoomAdminInterface {
    suspend fun actionAsync():Deferred<String>
    suspend fun anotherActionAsync():Deferred<String>
}

class RoomAdminImpl: RoomAdminInterface {
    override suspend fun actionAsync(): Deferred<String> {
        return GlobalScope.async {
            delay(3000)
            ""
        }
    }
    override suspend fun anotherActionAsync(): Deferred<String> {
        return GlobalScope.async {
            delay(3000)
            ""
        }
    }
}