package com.jesse.lazyload

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.item_fragment.*

/**
 * @author Jesse
 * @package com.jesse.lazyload
 * @fileName TabFragment.kt
 * @date on 2019-10-21
 * @qq 529840155
 **/

class TabFragment : Fragment() {

    companion object {

        val TAG = "TabFragment"

        fun newInstance(index: Int): TabFragment {

            val args = Bundle()
            args.putInt("index", index)
            val fragment = TabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var index: Int = 0

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        index = arguments!!.getInt("index")
    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "fragment$index == >onAttach")
        super.onAttach(context)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d(TAG, "fragment$index == >setUserVisibleHint")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "fragment$index == >onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "fragment$index == >onActivityCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "fragment$index == >onCreateView")
        return inflater.inflate(R.layout.item_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "fragment$index == >onViewCreated")
        tv_title.text = "Tab$index"
    }

    override fun onResume() {
        Log.d(TAG, "fragment$index == >onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "fragment$index == >onPause")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d(TAG, "fragment$index == >onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "fragment$index == >onDestroy")
        super.onDestroy()
    }
}