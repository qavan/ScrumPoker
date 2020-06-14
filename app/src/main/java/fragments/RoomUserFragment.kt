package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.qavan.sp.R
import viewmodels.RoomAdminViewModel
import viewmodels.RoomUserViewModel

class RoomUserFragment : Fragment() {

    private lateinit var viewModel: RoomUserViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(RoomUserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_room_user, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
        viewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }
}