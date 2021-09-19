package com.test.app.movies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MovieApplication: Application() {

    companion object {
        private var instance: MovieApplication? = null
        val TAG = MovieApplication::class.java.simpleName
        fun getInstance(): MovieApplication {
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}