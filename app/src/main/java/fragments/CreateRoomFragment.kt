package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.qavan.sp.R
import viewmodels.CreateRoomViewModel

class CreateRoomFragment : Fragment() {

    private lateinit var createRoomViewModel: CreateRoomViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        createRoomViewModel =
                ViewModelProviders.of(this).get(CreateRoomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create_room, container, false)
//        val textView: TextView = root.findViewById(R.id.text_slideshow)
        createRoomViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }
}