package com.mhp

import android.app.Application
import android.util.Log
import com.mhp.superawsometaskapp.injection.AwsomeComponent
import com.mhp.superawsometaskapp.injection.AwsomeModule
import com.mhp.superawsometaskapp.injection.DaggerAwsomeComponent

/**
 * Custom implementation of [Application] that is registered in the manifest file of the project.
 * This class holds an instance of [AwsomeComponent] inside a companion object that can be used to
 * trigger dependency injection using Dagger.
 */
class AwsomeApp : Application() {

    init {
        context = this
    }


    override fun onCreate() {
        // Build the Dagger component that is necessary for the dependency injection.
        awsomeComponent = DaggerAwsomeComponent.builder().awsomeModule(AwsomeModule()).build()
        super.onCreate()
    }

    companion object {
        lateinit var context: AwsomeApp
        /**
         * The Tag that can be used for logging
         */
        val TAG = AwsomeApp::class.java.simpleName
        /**
         * Can be used by other classes to trigger dependency injection using Dagger.
         */
        var awsomeComponent: AwsomeComponent? = null
            get() {
                if (field == null) {
                    Log.e(TAG, "trying to get injection component before it was initialized")
                }
                return field
            }
    }
}