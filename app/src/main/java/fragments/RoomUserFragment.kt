package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.qavan.sp.R
import viewmodels.RoomUserViewModel
import viewmodels.UserState

class RoomUserFragment : Fragment() {
    private val viewModel = RoomUserViewModel()
    private lateinit var tvRoomStatus: TextView
    private lateinit var tvRoomTheme: TextView
    private lateinit var tvAverage: TextView
    private lateinit var btnVote1: Button

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_room_user, container, false)
        tvRoomStatus = root.findViewById(R.id.tvRoomStatus)
        tvRoomTheme = root.findViewById(R.id.tvRoomTheme)
        tvAverage = root.findViewById(R.id.tvAverage)
        btnVote1 = root.findViewById(R.id.btnVote1)
        btnVote1.setOnClickListener {
            viewModel.someAction()
        }
        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                when(state) {
                    is UserState.Default -> {}
                    is UserState.VoteSomeActionState -> {}
                    is UserState.VoteSomeActionSuccessState -> {}
                    is UserState.ErrorState -> {handleError(state.errCode)}
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