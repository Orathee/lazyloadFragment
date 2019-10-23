package com.jesse.lazyload

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.ViewPager
import com.jesse.lazyload.comm.CommFragment
import com.jesse.lazyload.comm.FragmentWithViewPager
import com.jesse.lazyload.comm.InnerFragment
import com.jesse.lazyload.x.XFragment
import com.jesse.lazyload.x.XFragmentWithViewPager
import com.jesse.lazyload.x.XTabFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fragments = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragmentsAndViewPager()
        initViewObservables()
    }

    private fun initViewObservables() {

        radio_group.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {

                R.id.rb_1 -> view_pager.currentItem = 0
                R.id.rb_2 -> view_pager.currentItem = 1
                R.id.rb_3 -> view_pager.currentItem = 2
                R.id.rb_4 -> view_pager.currentItem = 3
                else -> view_pager.currentItem = 0
            }
        }

//        radio_group.check(R.id.rb_1)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                when (position) {

                    0 -> radio_group.check(R.id.rb_1)
                    1 -> radio_group.check(R.id.rb_2)
                    2 -> radio_group.check(R.id.rb_3)
                    3 -> radio_group.check(R.id.rb_4)
                    else -> radio_group.check(R.id.rb_1)
                }
            }
        })
    }

    private fun initFragmentsAndViewPager() {

        fragments.clear()
        fragments.add(XFragment.newInstance(1))
        fragments.add(XFragmentWithViewPager.newInstance(2))
        fragments.add(XFragment.newInstance(3))
        fragments.add(XFragment.newInstance(4))

        view_pager.adapter = XTabFragmentAdapter(supportFragmentManager, fragments)
//        view_pager.offscreenPageLimit = 0
    }

    fun setFragmentLifeCircle() {

//        supportFragmentManager.beginTransaction().setMaxLifecycle()
    }
}
