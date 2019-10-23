package com.jesse.lazyload.comm

import android.os.Bundle
import android.util.Log
import android.view.View
import com.jesse.lazyload.R
import kotlinx.android.synthetic.main.item_fragment.*

/**
 * @author Jesse
 * @package com.jesse.lazyload.comm
 * @fileName CommFragment.kt
 * @date on 2019-10-21
 * @qq 529840155
 **/
class CommFragment : CommLazyloadFragment() {

    companion object {

        fun newInstance(index: Int): CommFragment {

            val args = Bundle()
            args.putInt("index", index)
            val fragment = CommFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onFragmentFirstVisible() {

        Log.d(TAG, "lazyloadFragment$index ==> onFragmentFirstVisible")
    }

    override fun initView(view: View) {

    }

    override fun getLayoutResId(): Int = R.layout.item_fragment
}