package com.yf.yfscantools.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yf.base.ext.loadUrl
import com.yf.base.ext.onClick
import com.yf.base.ui.fragment.BaseFragment
import com.yf.base.utils.AppPrefsUtils
import com.yf.provider.common.ProviderConstant
import com.yf.provider.common.afterLogin
import com.yf.provider.common.isLogined
import com.yf.user.ui.activity.UserInfoActivity
import com.yf.yfscantools.R
import com.yf.yfscantools.SettingActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : BaseFragment(), View.OnClickListener {

    //启动 fragment时
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    //创建完成时
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    /*
        初始化视图
     */
    private fun initView() {

        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)

//        mWaitPayOrderTv.onClick(this)
//        mWaitConfirmOrderTv.onClick(this)
//        mCompleteOrderTv.onClick(this)
//        mAllOrderTv.onClick(this)
//        mAddressTv.onClick(this)
//        mShareTv.onClick(this)
        mSettingTv.onClick(this)


    }

    override fun onStart() {
        super.onStart()
        loadData()
    }


    /*
        加载初始数据
     */
    private fun loadData() {
        if (isLogined()) {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }

    }

    /*
        点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                afterLogin {
                    startActivity<UserInfoActivity>()
                }
            }

//            R.id.mWaitPayOrderTv -> {
//                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
//            }
//            R.id.mWaitConfirmOrderTv -> {
//                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
//            }
//            R.id.mCompleteOrderTv -> {
//                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
//            }
//            R.id.mAllOrderTv -> {
//                afterLogin {
//                    startActivity<OrderActivity>()
//                }
//            }
//
//            R.id.mAddressTv -> {
//                afterLogin {
//                    startActivity<ShipAddressActivity>()
//                }
//            }
//            R.id.mShareTv -> {
//                toast(R.string.coming_soon_tip)
//            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }
}