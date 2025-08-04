package com.org.rickandmorty.app

import android.app.Application
import com.org.rickandmorty.provider.BuildInfoProvider
import com.org.rickandmorty.provider.BuildInfoProviderImpl
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application(), BuildInfoProvider by BuildInfoProviderImpl() {

    override fun onCreate() {
        super.onCreate()
        if (isDebug) {
            Timber.plant(RickAndMortyDebugTree)
        }
    }
}
