package com.plcoding.spotifycloneyt.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.adapters.SwipeSongAdapter
import com.plcoding.spotifycloneyt.exoplayer.MusicServiceConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module // to tell Dagger that this is a module class
@InstallIn(ApplicationComponent::class) // parameter requires component which specifies the lifetime of dependencies, so here ApplicationComponent restricts the lifetime of dependencies to the lifetime of the application
// todo: Just a quick note, ApplicationComponent is deprecated. Use SingletonComponent in its place and all will be good. I think the reason that this is deprecated is that the Singleton component is more descriptive.
object AppModule {

    @Singleton
    @Provides
    fun provideMusicServiceConnection(
        @ApplicationContext context: Context
    ) = MusicServiceConnection(context)

    @Singleton
    @Provides
    fun provideSwipeSongAdapter() = SwipeSongAdapter()

    @Singleton // makes sure only single instance is created even when injected(called) multiple times
    @Provides // denotes that we want to provide something with this function
    fun provideGlideInstance(
        // the below context is known by dagger-hilt
        // bcoz SpotifyApplication class was annotated as @HiltAndroidApp
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .diskCacheStrategy(DiskCacheStrategy.DATA) // so that images are cached with glide
    )
}