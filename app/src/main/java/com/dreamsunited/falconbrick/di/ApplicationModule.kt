package com.dreamsunited.falconbrick.di

import android.content.Context
import android.content.res.Resources
import com.dreamsunited.falconbrick.repository.Repository
import com.dreamsunited.falconbrick.repository.RepositoryImpl
import com.dreamsunited.falconbrick.utils.AssetManagerHelper
import com.dreamsunited.falconbrick.utils.AssetManagerHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    @Singleton
    fun provideAssetManager(resources: Resources): AssetManagerHelper =
        AssetManagerHelperImpl(resources)

    @Provides
    @Singleton
    fun provideRepository(assetManagerHelper: AssetManagerHelper): Repository =
        RepositoryImpl(assetManagerHelper, Realm.getInstance(
            RealmConfiguration.Builder()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        ))
}

