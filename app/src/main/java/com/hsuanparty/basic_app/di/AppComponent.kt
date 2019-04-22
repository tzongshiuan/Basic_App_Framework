package com.hsuanparty.basic_app.di

import android.app.Application
import com.hsuanparty.basic_app.MyApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/4/22
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuildersModule::class])
interface AppComponent {
    fun application(): Application
    fun inject(app: MyApplication)
}