package com.hsuanparty.basic_app.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hsuanparty.basic_app.databinding.FragmentMainBinding
import com.hsuanparty.basic_app.di.Injectable
import com.hsuanparty.basic_app.model.PreferencesHelper
import com.hsuanparty.basic_app.utils.LogMessage
import javax.inject.Inject


/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/4/22
 * Description: A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), Injectable {
    companion object {
        private val TAG = MainActivityFragment::class.java.simpleName
    }

    @Inject
    lateinit var mPreferences: PreferencesHelper

    private lateinit var mBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        LogMessage.D(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogMessage.D(TAG, "onCreateView()")

        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        initUI()
        initSetting()

        return mBinding.root
    }

    override fun onResume() {
        LogMessage.D(TAG, "onResume()")
        super.onResume()
    }

    override fun onPause() {
        LogMessage.D(TAG, "onPause()")
        super.onPause()
    }

    override fun onStop() {
        LogMessage.D(TAG, "onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        LogMessage.D(TAG, "onDestroy()")
        super.onDestroy()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LogMessage.D(TAG, "onActivityResult(), requestCode: $requestCode, resultCode: $resultCode")
    }

    private fun initUI() {
        LogMessage.D(TAG, "initUI()")
    }

    private fun initSetting() {
        LogMessage.D(TAG, "initSetting()")
    }

//    private fun startSearchFragment() {
//        SimpleDelayTask.after(Constants.LOAD_DATA_TIME) {
//            if (isSearchFinish) {
//                Navigation.findNavController(mBinding.root)
//                    .navigate(R.id.action_mainActivityFragment_to_unboxParityActivity)
//            } else {
//                startSearchFragment()
//            }
//        }
//    }
}
