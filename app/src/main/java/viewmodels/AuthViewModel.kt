package viewmodels

import androidx.lifecycle.MutableLiveData
import com.qavan.sp.R
import default
import kotlinx.coroutines.*
import set

sealed class AuthState {
    class Default:AuthState()
    class LoginSendingState:AuthState()
    class LoginSucceededState(): AuthState()
    class RegisterSendingState(): AuthState()
    class RegisterSucceededState(): AuthState()
    class ErrorState(val errCode: Int): AuthState()
}
class AuthViewModel {
    val state: MutableLiveData<AuthState> = MutableLiveData<AuthState>().default(initialValue = AuthState.Default())

    fun login(login:String, pass:String) {
        if (!validateLogin(login)) {
            state.set(AuthState.ErrorState(1))
            return
        }
        if (!validatePassword(pass)) {
            state.set(AuthState.ErrorState(2))
            return
        }
        val stateMachine = CoroutineScope(Dispatchers.IO).async {
            state.set(AuthState.LoginSendingState())
            val errorMessage: String = AuthRepositoryImpl().loginAsync(login, pass).await()
            if (errorMessage.isEmpty()) launch(Dispatchers.Main) {
                state.set(AuthState.LoginSucceededState())
            }
            else launch(Dispatchers.Main) {
                state.set(AuthState.ErrorState(3))
            }
        }
    }

    fun register(login:String, pass:String) {
        if (!validateLogin(login)) {
            state.set(AuthState.ErrorState(1))
            return
        }
        if (!validatePassword(pass)) {
            state.set(AuthState.ErrorState(2))
            return
        }
        val stateMachine = CoroutineScope(Dispatchers.IO).async {
            state.set(AuthState.RegisterSendingState())
            val errorMessage: String = AuthRepositoryImpl().loginAsync(login, pass).await()
            if (errorMessage.isEmpty()) launch(Dispatchers.Main) {
                state.set(AuthState.RegisterSucceededState())
            }
            else launch(Dispatchers.Main) {
                state.set(AuthState.ErrorState(4))
            }
        }
    }

    private fun validateLogin(login:String):Boolean {
        return login.length > 4
    }

    private fun validatePassword(pass:String):Boolean {
        return pass.length > 4
    }
}

interface AuthRepository {
    suspend fun loginAsync(login:String,password:String):Deferred<String>
    suspend fun registerAsync(login:String,password:String):Deferred<String>
}

class AuthRepositoryImpl: AuthRepository {
    override suspend fun loginAsync(login: String, password: String): Deferred<String> {
        return GlobalScope.async {
            delay(3000)
            ""
        }
    }

    override suspend fun registerAsync(login: String, password: String): Deferred<String> {
        return GlobalScope.async {
            delay(3000)
            ""
        }
    }
}
