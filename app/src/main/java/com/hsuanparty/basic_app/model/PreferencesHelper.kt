package com.hsuanparty.basic_app.model


interface PreferencesHelper {
    var developer: String

    fun readPreferences()
    fun savePreferences()
}