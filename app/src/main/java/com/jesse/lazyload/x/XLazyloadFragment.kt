package com.jesse.lazyload.x

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
 * @package com.jesse.dispatchUserVisiable
 * @fileName CommLazyloadFragment.kt
 * @date on 2019-10-21
 * @qq 529840155
 **/
abstract class XLazyloadFragment : Fragment() {

    companion object {

        val TAG = "XLazyloadFragment"
    }

    var rootView: View? = null
    /**
     * View创建
     */
    var mViewCreated = false

    /**
     * 第一次
     */
    var mFirstVisible = true
    var index = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "$TAG$index ==> onAttach, state = ${lifecycle.currentState}")
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        index = if (arguments == null) 0 else arguments!!.getInt("index")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResId(), container, false)
        }
        initView(rootView!!)
        //view创建完成
        mViewCreated = true
        if (index != 0)
            Log.d(TAG, "$TAG$index ==> onCreateView, state = ${lifecycle.currentState}")
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title?.text = "fragment$index"
//        Log.d(TAG, "lazyloadFragment$index ==> onViewCreated")
        Log.d(TAG, "$TAG$index ==> onViewCreated, state = ${lifecycle.currentState}")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
//        Log.d(TAG, "lazyloadFragment$index ==> onHiddenChanged = $hidden")
        Log.d(TAG, "$TAG$index ==> onHiddenChanged, state = ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()

        if(mFirstVisible) {
            mFirstVisible = false
            onFragmentFirstVisible()
        }
        onFragmentResume()
        Log.d(TAG, "$TAG$index ==> onResume, state = ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        onFragmentPause()
        Log.d(TAG, "$TAG$index ==> onPause, state = ${lifecycle.currentState}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mFirstVisible = true
//        Log.d(TAG, "lazyloadFragment$index ==> onDestroyView")
        Log.d(TAG, "$TAG$index ==> onDestroyView, state = ${lifecycle.currentState}")
    }


    protected fun onFragmentResume() {

        Log.d(TAG, "$TAG$index ==> onFragmentResume")
    }

    protected fun onFragmentPause() {

        Log.d(TAG, "$TAG$index ==> onFragmentPause")
    }

    abstract fun onFragmentFirstVisible()

    abstract fun getLayoutResId(): Int

    abstract fun initView(view: View)
}