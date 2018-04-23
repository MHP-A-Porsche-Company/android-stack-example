package com.mhp.superawsometaskapp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.mhp.superawsometaskapp.fragment.ListFragment_
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity

/**
 * Implementation of [AppCompatActivity] that uses android annotation for view binding and layout
 * inflation.
 */
@EActivity(R.layout.activity_main)
@SuppressLint("Registered")
open class MainActivity : AppCompatActivity() {
    /**
     * Gets called after the view binding via Android Annotation is completed.
     * Can be used to bind values from the view model to the views of this layout.
     * <br/>
     *In this case an instance of [com.mhp.superawsometaskapp.fragment.ListFragment] gets created
     * and displayed in the content area of the activity.
     */
    @AfterViews
    fun afterViews() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, ListFragment_.builder().build())
        fragmentTransaction.commit()
    }
}
