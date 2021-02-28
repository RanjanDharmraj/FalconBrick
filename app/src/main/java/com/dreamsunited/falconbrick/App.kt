package com.dreamsunited.falconbrick

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration

@HiltAndroidApp
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder().name("falcon.db").schemaVersion(1)
                .deleteRealmIfMigrationNeeded().build()
        )
    }
}