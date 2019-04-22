package com.hsuanparty.basic_app.di

import com.hsuanparty.basic_app.view.ui.MainActivityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/3/27
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivityFragment(): MainActivityFragment
}