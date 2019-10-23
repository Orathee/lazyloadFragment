package com.jesse.lazyload.comm

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

class InnerFragment : CommLazyloadFragment() {

    companion object {

        fun newInstance(index: Int): InnerFragment {

            val args = Bundle()
            args.putInt("index", index)
            val fragment = InnerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        Log.d(TAG, "fragmentManager ==> $fragmentManager")
    }

    override fun onFragmentFirstVisible() {

        Log.d(TAG, "InnerFragment$index ==> onFragmentFirstVisible")
    }

    override fun getLayoutResId(): Int = R.layout.item_fragment

    override fun initView(view: View) {

//        Log.d(TAG, "InnerFragment$index ==> initView")

    }


}