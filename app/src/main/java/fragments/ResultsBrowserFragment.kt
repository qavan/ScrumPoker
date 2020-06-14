package fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.qavan.sp.R
import viewmodels.ResultsBrowserViewModel

class ResultsBrowserFragment : Fragment() {
    private lateinit var browserViewModel: ResultsBrowserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        browserViewModel = ViewModelProviders.of(this).get(ResultsBrowserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_results_browser, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
        browserViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }

}