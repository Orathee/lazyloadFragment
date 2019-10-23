package com.jesse.lazyload.x

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.jesse.lazyload.R

/**
 * @author Jesse
 * @package com.jesse.lazyload.comm
 * @fileName FragmentWithViewPager.kt
 * @date on 2019-10-21
 * @qq 529840155
 **/
class XFragmentWithViewPager : XLazyloadFragment() {

    companion object {

        fun newInstance(index: Int): XFragmentWithViewPager {

            val args = Bundle()
            args.putInt("index", index)
            val fragment = XFragmentWithViewPager()
            fragment.arguments = args
            return fragment
        }
    }

    var fragmentPagerAdapter: FragmentPagerAdapter? = null

    override fun onFragmentFirstVisible() {

        Log.d(TAG, "FragmentWithViewPager ==> onFragmentFirstVisible")
    }

    override fun getLayoutResId(): Int = R.layout.item_fragment_container

    override fun initView(view: View) {

        fragments.add(XInnerFragment.newInstance(21))
        fragments.add(XInnerFragment.newInstance(22))

        fragmentPagerAdapter = XTabFragmentAdapter(childFragmentManager, fragments)

        view.findViewById<ViewPager>(R.id.view_pager).adapter = fragmentPagerAdapter
//        Log.d(TAG, "fragmentManager ==> $childFragmentManager")
    }

    val fragments = ArrayList<Fragment>()

}