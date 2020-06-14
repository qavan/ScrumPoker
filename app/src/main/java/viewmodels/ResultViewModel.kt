package viewmodels

import androidx.lifecycle.MutableLiveData
import default
import kotlinx.coroutines.*
import set

sealed class ResultState {
    class Default:ResultState()
    class VoteSomeActionState:ResultState()
    class VoteSomeActionSuccessState:ResultState()
    class ErrorState(val errCode:Int):ResultState()
}

class ResultViewModel {
    val state: MutableLiveData<ResultState> = MutableLiveData<ResultState>().default(initialValue = ResultState.Default())

    fun someAction() {
        val stateMachine = CoroutineScope(Dispatchers.IO).async {
            state.set(ResultState.VoteSomeActionState())
            val errorMessage = ResultImpl().actionAsync().await()
            if (errorMessage.isEmpty()) launch(Dispatchers.Main) {
                state.set(ResultState.VoteSomeActionSuccessState())
            }
            else launch(Dispatchers.Main) {
                state.set(ResultState.ErrorState(1))
            }
        }
    }
}

interface ResultInterface {
    suspend fun actionAsync(): Deferred<String>
}

class ResultImpl: ResultInterface {
    override suspend fun actionAsync(): Deferred<String> {
        return GlobalScope.async {
            delay(1500)
            ""
        }
    }
}