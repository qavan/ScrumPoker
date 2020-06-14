package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.qavan.sp.R
import viewmodels.RoomsBrowserViewModel

class RoomsBrowserFragment : Fragment() {

    private lateinit var roomsBrowserViewModel: RoomsBrowserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        roomsBrowserViewModel = ViewModelProviders.of(this).get(RoomsBrowserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rooms_browser, container, false)
//        val textView: TextView = root.findViewById(R.id.text_gallery)
        roomsBrowserViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }
}