package viewmodels

import androidx.lifecycle.MutableLiveData
import default
import kotlinx.coroutines.*
import set

sealed class UserState {
    class Default:UserState()
    class VoteSomeActionState:UserState()
    class VoteSomeActionSuccessState:UserState()
    class ErrorState(val errCode:Int):UserState()
}

class RoomUserViewModel {
    val state: MutableLiveData<UserState> = MutableLiveData<UserState>().default(initialValue = UserState.Default())

    fun someAction() {
        val stateMachine = CoroutineScope(Dispatchers.IO).async {
            state.set(UserState.VoteSomeActionState())
            val errorMessage = RoomUserImpl().actionAsync().await()
            if (errorMessage.isEmpty()) launch(Dispatchers.Main) {
                state.set(UserState.VoteSomeActionSuccessState())
            }
            else launch(Dispatchers.Main) {
                state.set(UserState.ErrorState(1))
            }
        }
    }
}

interface RoomUserInterface {
    suspend fun actionAsync(): Deferred<String>
}

class RoomUserImpl: RoomUserInterface {
    override suspend fun actionAsync(): Deferred<String> {
        return GlobalScope.async {
            delay(1500)
            ""
        }
    }
}