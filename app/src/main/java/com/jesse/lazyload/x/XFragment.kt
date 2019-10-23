package com.jesse.lazyload.x

import android.os.Bundle
import android.util.Log
import android.view.View
import com.jesse.lazyload.R

/**
 * @author Jesse
 * @package com.jesse.lazyload.comm
 * @fileName CommFragment.kt
 * @date on 2019-10-21
 * @qq 529840155
 **/
class XFragment : XLazyloadFragment() {

    companion object {

        fun newInstance(index: Int): XFragment {

            val args = Bundle()
            args.putInt("index", index)
            val fragment = XFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onFragmentFirstVisible() {

        Log.d(TAG, "XLazyloadFragment$index ==> onFragmentFirstVisible")
    }

    override fun initView(view: View) {

    }

    override fun getLayoutResId(): Int = R.layout.item_fragment
}