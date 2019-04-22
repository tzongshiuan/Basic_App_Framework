package com.hsuanparty.basic_app.di

import com.hsuanparty.basic_app.view.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/4/22
 */
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}