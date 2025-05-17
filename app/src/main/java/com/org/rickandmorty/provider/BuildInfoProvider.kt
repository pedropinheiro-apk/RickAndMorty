package com.org.rickandmorty.provider

import com.org.rickandmorty.BuildConfig

interface BuildInfoProvider {
    val isDebug : Boolean
}

class BuildInfoProviderImpl : BuildInfoProvider {

    override val isDebug: Boolean
        get() = BuildConfig.DEBUG
}
