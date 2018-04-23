package com.mhp.superawsometaskapp.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.EditText
import com.mhp.AwsomeApp
import com.mhp.superawsometaskapp.R
import com.mhp.superawsometaskapp.viewmodel.CreateItemViewModel
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

/**
 * An implementation of [DialogFragment] to allow the user to create a new item of
 * [com.mhp.superawsometaskapp.model.ListItem]. The fragment uses an instance of
 * [CreateItemViewModel] to control the business logic using [io.reactivex.Observable]s.
 */
@EFragment(R.layout.fragment_dialog_create)
open class CreateItemDialogFragment : DialogFragment() {

    /**
     * The view model that holds the business logic
     */
    @Inject
    lateinit var itemViewModel: CreateItemViewModel
    /**
     * The text view that takes the user input
     */
    @ViewById(R.id.text)
    lateinit var editText: EditText

    init {
        AwsomeApp.awsomeComponent?.inject(this)
    }

    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        dialog.window?.attributes?.windowAnimations = R.style.AlertDialogAnimation
    }

    /**
     * Gets called after the view binding via Android Annotation is completed.
     * Can be used to bind values from the view model to the views of this layout.
     */
    @AfterViews
    fun afterViews() {
        dialog.setCanceledOnTouchOutside(true)
    }

    /**
     * Gets called when the submit button gets clicked.
     * Values will be persisted and the view terminated.
     */
    @Click(R.id.ok)
    fun okClicked() {
        itemViewModel.createItem(editText.text.toString())
        dismiss()
    }
}