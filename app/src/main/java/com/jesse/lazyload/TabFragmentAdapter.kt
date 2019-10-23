package com.jesse.lazyload

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
class TabFragmentAdapter(fragmentManager: FragmentManager, private val fragments: List<Fragment>) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {

        return fragments[position]
    }

    override fun getCount(): Int {

        return fragments.size
    }

}