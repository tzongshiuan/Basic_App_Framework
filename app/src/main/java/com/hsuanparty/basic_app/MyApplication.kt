package com.hsuanparty.basic_app

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.hsuanparty.basic_app.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

@Suppress("DEPRECATION")
class MyApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//            Stetho.initializeWithDefaults(this)
//        }

        // Dagger2
        AppInjector.init(this)

//        Fabric.with(this, Crashlytics())
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentInjector
    }
}