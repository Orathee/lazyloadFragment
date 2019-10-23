package com.jesse.lazyload.comm

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.jesse.lazyload.R
import kotlinx.android.synthetic.main.item_fragment_container.*

/**
 * @author Jesse
 * @package com.jesse.lazyload.comm
 * @fileName FragmentWithViewPager.kt
 * @date on 2019-10-21
 * @qq 529840155
 **/
class FragmentWithViewPager : CommLazyloadFragment() {

    companion object {

        fun newInstance(index: Int): FragmentWithViewPager {

            val args = Bundle()
            args.putInt("index", index)
            val fragment = FragmentWithViewPager()
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

        fragments.add(InnerFragment.newInstance(21))
        fragments.add(InnerFragment.newInstance(22))

        fragmentPagerAdapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment = fragments[position]

            override fun getCount(): Int = fragments.size
        }

        view.findViewById<ViewPager>(R.id.view_pager).adapter = fragmentPagerAdapter
//        Log.d(TAG, "fragmentManager ==> $childFragmentManager")
    }

    val fragments = ArrayList<Fragment>()

}