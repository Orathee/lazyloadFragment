package com.jesse.lazyload.x

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jesse.lazyload.R

/**
 * @author Jesse
 * @package com.jesse.lazyload.comm
 * @fileName InnerFragment.kt
 * @date on 2019-10-21
 * @qq 529840155
 **/

class XInnerFragment : XLazyloadFragment() {

    companion object {

        fun newInstance(index: Int): XInnerFragment {

            val args = Bundle()
            args.putInt("index", index)
            val fragment = XInnerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onFragmentFirstVisible() {

        Log.d(TAG, "XInnerFragment$index ==> onFragmentFirstVisible")
    }

    override fun getLayoutResId(): Int = R.layout.item_fragment

    override fun initView(view: View) {

//        Log.d(TAG, "InnerFragment$index ==> initView")

    }


}