package com.hsuanparty.basic_app.view.ui

import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hsuanparty.basic_app.BuildConfig
import com.hsuanparty.basic_app.R
import com.hsuanparty.basic_app.di.Injectable
import com.hsuanparty.basic_app.model.PreferencesHelper
import com.hsuanparty.basic_app.utils.*
import com.hsuanparty.basic_app.utils.networkChecker.NetworkChangeReceiver
import com.hsuanparty.basic_app.utils.networkChecker.NetworkState
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap


/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/4/22
 */
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {

    companion object {
        private val TAG = MainActivity::class.java.simpleName

        private const val PERMISSION_ALL = 1

        private const val MSG_NETWORK_DIALOG = 16

        class NetworkHandler(private val activity: MainActivity): Handler() {

            override fun handleMessage(msg: Message) {
                LogMessage.D(TAG, "handleMessage msg.what = " + msg.what)
                when (msg.what) {
                    MSG_NETWORK_DIALOG -> {
                        activity.showNetworkCheckDialog()
                    }
                }
            }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mPreferences: PreferencesHelper

    private val networkReceiver: NetworkChangeReceiver by lazy { NetworkChangeReceiver() }

    private var networkDialog: AlertDialog? = null

    private val handler: Handler = NetworkHandler(this)

    private val mPermissions = arrayOf(
        android.Manifest.permission.CAMERA,
//        android.Manifest.permission.ACCESS_FINE_LOCATION,
//        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.RECORD_AUDIO
//        android.Manifest.permission.GET_ACCOUNTS
    )

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        LogMessage.D(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        initSettings()

        if (Constants.IS_ENABLE_NERWORK_CHECKER) {
            NetworkState.isNetworkConnected.observe(this, Observer {
                LogMessage.D(TAG, "Is network connected = ${it!!}")

                if (!it) {
                    handler.sendEmptyMessageDelayed(MSG_NETWORK_DIALOG, 1500)
                } else {
                    handler.removeMessages(MSG_NETWORK_DIALOG)
                }
            })

            val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            registerReceiver(networkReceiver, filter)
        }

        if(!hasPermissions()){
            ActivityCompat.requestPermissions(this, mPermissions, PERMISSION_ALL)
        }

        // Only show log message in debug mode
        Constants.IS_DEBUG_MODE = BuildConfig.DEBUG
        LogMessage.isShowLog = BuildConfig.DEBUG

        val leetCode = LeetCode()
        leetCode.start()

        val rxjava = RxJavaTest()
        rxjava.test()

        val map = HashMap<Char, Boolean>()
        map.put('c', false)
        map.remove('c')
        val s = "123".intern()

//        val queue = ArrayDeque<Int>()
//        queue.push(6)

//        val hsuan = Hsuan()
//        val method = hsuan.javaClass.getDeclaredMethod("getName", String::class.java)
//        method.invoke(hsuan, "YYYYYYYYYYYYYY")
//
//        val heap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            PriorityQueue<Int> { n1, n2 -> n1 - n2}
//        } else {
//            TODO("VERSION.SDK_INT < N")
//        }
//        heap.poll()
//
//        val list = ArrayList<Int>()
//        list.reverse()

//        val array = Array<IntArray>(4) {
//            IntArray(2)
//        }
//        array[0] = intArrayOf(1, 3)
//        array[1] = intArrayOf(2, 6)
//        array[2] = intArrayOf(8, 10)
//        val a = intArrayOf(15, 18)
    }

    open class Hsuan {
        open fun getName(str: String) {
            Log.d("1212", "GGGGGGGGGGGGGGGGGGGGGGGG $str")
        }
    }

    override fun onStart() {
        LogMessage.D(TAG, "onStart()")
        super.onStart()
    }

    override fun onResume() {
        LogMessage.D(TAG, "onResume()")
        super.onResume()
    }

    override fun onNewIntent(intent: Intent?) {
        LogMessage.D(TAG, "onNewIntent(), action = ${intent?.action}")
        super.onNewIntent(intent)
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

        unregisterReceiver(networkReceiver)
    }

    override fun onBackPressed() {
        LogMessage.D(TAG, "onBackPressed()")
        super.onBackPressed()
    }

//    override fun onUserInteraction() {
//        LogMessage.D(TAG, "onUserInteraction()")
//        super.onUserInteraction()
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        LogMessage.D(TAG, "onActivityResult(), requestCode: $requestCode, resultCode: $resultCode")
//
//        val fragment = supportFragmentManager.findFragmentById(R.id.mainActivityFragment)
//        fragment?.onActivityResult(requestCode, resultCode, data)
//    }

    private fun showNetworkCheckDialog() {
        if (networkDialog == null) {
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(false)
            builder.setTitle(getString(R.string.txt_network_check))
            builder.setMessage(getString(R.string.msg_network_check))
            builder.setPositiveButton(getString(R.string.txt_ok)) { _, _ ->
                this.finish()
            }
            networkDialog = builder.create()
        }
        networkDialog?.show()
    }

    private fun hasPermissions(): Boolean {
        for (permission in mPermissions) {
            if (this.let { ContextCompat.checkSelfPermission(it, permission) } != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_ALL -> {
                var isAllGrant = true
                for (result in grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        isAllGrant = false
                        break
                    }
                }

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && isAllGrant) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Request permission failed, forced to close app", Toast.LENGTH_SHORT).show()
                    SimpleDelayTask.after(Constants.PERMISSION_DENY_CLOSE_TIME) {
                        this.finish()
                    }
                }
                return
            }
        }
    }

    private fun initUI() {
        LogMessage.D(TAG, "initUI()")
    }

    private fun initSettings() {
        LogMessage.D(TAG, "initSettings()")
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
