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
import viewmodels.CreateRoomViewModel
import viewmodels.CreateState

class CreateRoomFragment : Fragment() {
    private val viewModel = CreateRoomViewModel()
    private lateinit var etRoomName: EditText
    private lateinit var etRoomPassword: EditText
    private lateinit var btnCreateRoom: Button
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_create_room, container, false)
        etRoomName = root.findViewById(R.id.etRoomName)
        etRoomPassword = root.findViewById(R.id.etRoomPassword)
        btnCreateRoom = root.findViewById(R.id.btnRoomCreate)
        btnCreateRoom.setOnClickListener {
            viewModel.create(etRoomName.text.toString(),etRoomPassword.text.toString())
        }
        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                when(state) {
                    is CreateState.Default -> {}
                    is CreateState.CreateSendingState -> {}
                    is CreateState.CreateSuccessState -> {}
                    is CreateState.ErrorState -> {handleError(state.errCode)}
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