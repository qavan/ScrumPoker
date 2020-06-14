package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.qavan.sp.R
import viewmodels.ResultState
import viewmodels.ResultViewModel
class ResultFragment: Fragment() {
    private val viewModel = ResultViewModel()
    private lateinit var tvRoomName: TextView
    private lateinit var tvRoomTheme: TextView
    private lateinit var tvRoomAverage: TextView
    private lateinit var tvRoomDate: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_result, container, false)
        tvRoomName = root.findViewById(R.id.tvRoomNameValue)
        tvRoomTheme = root.findViewById(R.id.tvRoomThemeValue)
        tvRoomAverage = root.findViewById(R.id.tvRoomAverageValue)
        tvRoomDate = root.findViewById(R.id.tvRoomDateValue)

        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                when(state) {
                    is ResultState.Default -> {}
                    is ResultState.VoteSomeActionState -> {}
                    is ResultState.VoteSomeActionSuccessState -> {}
                    is ResultState.ErrorState -> {handleError(state.errCode)}
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