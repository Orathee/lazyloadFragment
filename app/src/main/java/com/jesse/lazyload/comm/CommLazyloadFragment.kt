package com.jesse.lazyload.comm

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
abstract class CommLazyloadFragment : Fragment() {

    companion object {

        val TAG = "CommLazyloadFragment"
    }

    var rootView: View? = null
    /**
     * View创建
     */
    var mViewCreated = false
    /**
     * 用户可见
     */
    var mUserVisible = false

    /**
     * 第一次
     */
    var mFirstVisible = true
    var index = 0

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
            Log.d(TAG, "lazyloadFragment$index ==> onCreateView")

        /**
         * 本次分发主要用于分发默认tab的可见状态
         * 生命周期setUserVisibleHint: true-》onAttach-》onCreate-》onCreateView-》onResume
         * 默认 Tab getUserVisibleHint() = true !isHidden() = true
         * 对于非默认 tab 或者非默认显示的 Fragment 在该生命周期中只做了 isViewCreated 标志位设置 可见状态将不会在这里分发
         */
        if (!isHidden && userVisibleHint) {
            dispatchUserVisible(true)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title?.text = "fragment$index"
    }


    /**
     * fragment的可见性
     * 以下两种情况下会被调用
     * 1、在切换tab的时候，会先于所有fragment的其他生命周期，先调用这个函数，可以看log，
     *    对于默认 tab 和 间隔 checked tab 需要等到 isViewCreated = true
     * 2、对于之前已经调用过setUserVisibleHint 方法的fragment后，让fragment从可见到不可见之间状态的变化
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        /**
         * 对于情况1，不予处理，用mViewCreated进行判断，如果为false，说明没有被创建
         */
        if (mViewCreated) {
            /**
             * 对于情况2，分两种：
             * 1）可见->不可见
             * 首先，它必须是可见的（mUserVisible = true）
             * 并且只有当可见状态进行改变的时候才需要切换（mUserVisible != isVisibleToUser）
             * 从而避免重复调用
             * 2) 不可见->可见
             * 与1）相反
             */
            if (!isVisibleToUser && mUserVisible) {
                dispatchUserVisible(false)
            } else if (isVisibleToUser && !mUserVisible) {
                dispatchUserVisible(true)
            }
        }
        Log.d(TAG, "lazyloadFragment$index ==> setUserVisibleHint=$isVisibleToUser")
    }

    /**
     * 用FragmentTransaction来控制fragment的hide和show时，
     * 那么这个方法就会被调用。每当你对某个Fragment使用hide
     * 或者是show的时候，那么这个Fragment就会自动调用这个方法。
     * 你会发现使用hide和show这时fragment的生命周期不再执行，
     * 不走任何的生命周期，
     * 这样在有的情况下，数据将无法通过生命周期方法进行刷新，
     * 所以你可以使用onHiddenChanged方法来解决这问题。
     * @param hidden
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        dispatchUserVisible(!hidden)
        Log.d(TAG, "lazyloadFragment$index ==> onHiddenChanged = $hidden")
    }

    override fun onResume() {
        super.onResume()
        /**
         * 在滑动或调转的过程中，第一次创建fragment的时候均会回调onResume方法
         * 如：tab1(fragment)->tab2,此时会调用tab3的onResume,所以，此时并不需要
         * 去调用dispatchUserVisible(true)
         */
        if (!mFirstVisible) {

            /**
             * 从当前Activity跳转到其他Activity再返回时，也会回调所有fragment的onResume
             * 此时只需处理当前可见的fragment
             */
            if(!isHidden && !mUserVisible && userVisibleHint) {

                dispatchUserVisible(true)
            }

        }
//        Log.d(TAG, "lazyloadFragment$index ==> onResume")
    }

    /**
     * 只有当前页面由可见到不可见时才需要调用dispatchUserVisible
     * mUserVisible && userVisibleHint（）能够限定当前fragment是可见的
     */
    override fun onPause() {
        super.onPause()
        if (mUserVisible && userVisibleHint) {
            dispatchUserVisible(false)
        }
        Log.d(TAG, "lazyloadFragment$index ==> onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewCreated = false
        mFirstVisible = true
        mUserVisible = false
        Log.d(TAG, "lazyloadFragment$index ==> onDestroyView")
    }

    /**
     * 统一分发用户可见信息
     */
    private fun dispatchUserVisible(isVisible: Boolean) {

        //不分发
        if (mUserVisible == isVisible) {
            return
        }

        //父类不可见时return
        if(isVisible && !isParentVisible()) {
            return
        }
        mUserVisible = isVisible
        if (isVisible) {

            //第一次可见
            if (mFirstVisible) {
                mFirstVisible = false
                onFragmentFirstVisible()
            }
            onFragmentResume()
            dispatchChildVisibleState(true)
        } else {
            //不可见
            onFragmentPause()
            dispatchChildVisibleState(false)
        }
    }

    private fun isParentVisible(): Boolean {

        val parent = parentFragment
        return parent == null || (parent  as CommLazyloadFragment).mUserVisible
    }

    private fun dispatchChildVisibleState(visible: Boolean) {
        val fm = childFragmentManager

        val fragments = fm.fragments
        for (fragment in fragments) {
            if (fragment is CommLazyloadFragment &&
                !fragment.isHidden() &&
                fragment.getUserVisibleHint()
            ) {
                fragment.dispatchUserVisible(visible)
            }
        }
    }

    /**
     * 做网络或本地数据加载或中断后再次请求数据
     */
    protected fun onFragmentResume() {

        Log.d(TAG, "lazyloadFragment$index ==> onFragmentResume")
    }

    /**
     * 数据中断操作
     */
    protected fun onFragmentPause() {

        Log.d(TAG, "lazyloadFragment$index ==> onFragmentPause")
    }

    /**
     * UI初始化
     */
    abstract fun onFragmentFirstVisible()

    abstract fun getLayoutResId(): Int

    abstract fun initView(view: View)
}