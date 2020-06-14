package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.qavan.sp.R
import disable
import enable
import viewmodels.AuthViewModel
import viewmodels.AuthState

class AuthFragment : Fragment() {
    private var viewModel = AuthViewModel()
    private lateinit var etLogin: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_auth, container, false)
        etLogin = root.findViewById(R.id.etRoomName)
        etPass = root.findViewById(R.id.etRoomPassword)
        btnLogin = root.findViewById(R.id.btnLogin)
        btnRegister = root.findViewById(R.id.btnRegister)
        btnLogin.setOnClickListener {
//            Navigation.findNavController(root).navigate(R.id.nav_results)
            viewModel.login(etLogin.text.toString(),etPass.text.toString())
        }
        btnRegister.setOnClickListener {
            viewModel.register(etLogin.text.toString(),etPass.text.toString())
        }
        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                when(state) {
                    is AuthState.Default -> {}
                    is AuthState.LoginSendingState -> {
                        etLogin.disable();etPass.disable();btnLogin.disable();btnRegister.disable()
                    }
                    is AuthState.LoginSucceededState -> {
                        etLogin.enable();etPass.enable();btnLogin.enable();btnRegister.enable()
                        Toast.makeText(this.context,"Successful login",Toast.LENGTH_SHORT).show()
                    }
                    is AuthState.RegisterSendingState -> {
                        etLogin.disable();etPass.disable();btnLogin.disable();btnRegister.disable()
                    }
                    is AuthState.RegisterSucceededState -> {
                        etLogin.enable();etPass.enable();btnLogin.enable();btnRegister.enable()
                        Toast.makeText(this.context,"Successful register",Toast.LENGTH_SHORT).show()
                    }
                    is AuthState.ErrorState -> {
                        etLogin.enable();etPass.enable();btnLogin.enable();btnRegister.enable()
                        handleError(state.errCode)
                    }
                }
            }
        )
        return root
    }

    private fun handleError(errCode: Int) {
        when(errCode) {
            1 -> {
                Toast.makeText(this.context,getString(R.string.invalidLogin),Toast.LENGTH_SHORT).show()
            }
            2 -> {
                Toast.makeText(this.context,getString(R.string.invalidPassword),Toast.LENGTH_SHORT).show()
            }
            3 -> {
                Toast.makeText(this.context,getString(R.string.errDuringLogin),Toast.LENGTH_SHORT).show()
            }
            4 -> {
                Toast.makeText(this.context,getString(R.string.errDuringRegister),Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this.context,"Gotcha error with error code = $errCode",Toast.LENGTH_SHORT).show()
            }
        }
    }
}