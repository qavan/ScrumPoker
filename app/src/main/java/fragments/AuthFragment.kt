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
        etLogin = root.findViewById(R.id.etLogin)
        etPass = root.findViewById(R.id.etPassword)
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
                        disableViews()
                    }
                    is AuthState.LoginSucceededState -> {
                        enableViews()
                        Toast.makeText(this.context,"Successful login",Toast.LENGTH_SHORT).show()
                    }
                    is AuthState.ErrorState -> {
                        enableViews()
                        handleError(state.errCode)
                    }
                    is AuthState.RegisterSendingState -> {
                        disableViews()
                    }
                    is AuthState.RegisterSucceededState -> {
                        enableViews()
                        Toast.makeText(this.context,"Successful register",Toast.LENGTH_SHORT).show()
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
    private fun disableViews() {
        etLogin.isEnabled = false;
        etPass.isEnabled = false;
        btnLogin.isEnabled = false;
        btnRegister.isEnabled = false
    }
    private fun enableViews() {
        etLogin.isEnabled = true;
        etPass.isEnabled = true;
        btnLogin.isEnabled = true;
        btnRegister.isEnabled = true
    }
}