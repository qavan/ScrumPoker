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
import viewmodels.AdminState
import viewmodels.RoomAdminViewModel

class RoomAdminFragment : Fragment() {
    private val viewModel = RoomAdminViewModel()
    private lateinit var btnSomeAction: Button
    private lateinit var etTheme: EditText
    private lateinit var btnSetTheme: Button

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_room_admin, container, false)
        btnSomeAction = root.findViewById(R.id.btnAdminAction)
        etTheme = root.findViewById(R.id.etTheme)
        btnSetTheme = root.findViewById(R.id.btnAdminTheme)
        btnSomeAction.setOnClickListener {
            viewModel.someAction()
        }
        btnSomeAction.setOnClickListener {
            viewModel.someAnotherAction()
        }
        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                when(state) {
                    is AdminState.Default -> {}
                    is AdminState.VoteSomeActionState -> {}
                    is AdminState.VoteSomeActionSuccess -> {}
                    is AdminState.ErrorState -> {handleError(state.errCode)}
                }
            }
        )
        return root
    }
    private fun handleError(errCode:Int) {
        when(errCode) {
            1 -> {
                Toast.makeText(this.context,"Gotcha error with error code = $errCode", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this.context,"Gotcha error with error code = $errCode", Toast.LENGTH_SHORT).show()
            }
        }
    }
}