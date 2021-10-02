package com.plcoding.spotifycloneyt.di

import android.content.Context
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module // to tell Dagger that this is a module class
@InstallIn(ServiceComponent::class) // parameter requires component which specifies the lifetime of dependencies, so here ServiceComponent restricts the lifetime of dependencies to the lifetime of the service
object ServiceModule {

    @ServiceScoped // we will have the same instance of these audio attributes in our same service instance
    @Provides // denotes that we want to provide something with this function
    fun provideAudioAttributes() = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @ServiceScoped // we will have the same instance of these audio attributes in our same service instance
    @Provides // denotes that we want to provide something with this function
    fun provideExoPlayer(
        // the below context is known by dagger-hilt
        // bcoz SpotifyApplication class was annotated as @HiltAndroidApp
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes // Dagger-Hilt automatically takes AudioAttributes from above provider function
    ) = SimpleExoPlayer.Builder(context).build().apply {
        setAudioAttributes(audioAttributes, true)
        setHandleAudioBecomingNoisy(true)
    }

    @ServiceScoped // we will have the same instance of these audio attributes in our same service instance
    @Provides // denotes that we want to provide something with this function
    fun provideDataSourceFactory(
        // the below context is known by dagger-hilt
        // bcoz SpotifyApplication class was annotated as @HiltAndroidApp
        @ApplicationContext context: Context,
    ) = DefaultDataSourceFactory(context, Util.getUserAgent(context, "Music Player App"))
}