package com.jesse.lazyload.x

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author Jesse
 * @package com.jesse.lazyload
 * @fileName TabFragmentAdapter.kt
 * @date on 2019-10-21
 * @qq 529840155
 **/
class XTabFragmentAdapter(
    var fragmentManager: FragmentManager,
    private val fragments: List<Fragment>
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {

        return fragments[position]
    }

    override fun getCount(): Int {

        return fragments.size
    }

}