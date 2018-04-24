package com.mhp.superawsometaskapp.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.EditText
import com.mhp.superawsometaskapp.AwsomeApp
import com.mhp.superawsometaskapp.R
import com.mhp.superawsometaskapp.viewmodel.DefaultCreateItemViewModel
import com.mhp.superawsometaskapp.viewmodel.EditItemViewModel
import io.reactivex.disposables.Disposable
import org.androidannotations.annotations.*
import javax.inject.Inject

/**
 * An implementation of [DialogFragment] to allow the user to update an existing item of
 * [com.mhp.superawsometaskapp.model.ListItem]. The fragment uses an instance of
 * [DefaultCreateItemViewModel] to control the business logic using [io.reactivex.Observable]s.
 */
@EFragment(R.layout.fragment_dialog_edit)
open class EditItemDialogFragment : DialogFragment() {

    /**
     * The view model that holds the business logic
     */
    @Inject
    lateinit var itemViewModel: EditItemViewModel
    /**
     * The text view that takes the user input
     */
    @ViewById(R.id.text)
    lateinit var editText: EditText
    /**
     * The id of the instance of [com.mhp.superawsometaskapp.model.ListItem].
     * This value can be provided as argument when creating an instance of [EditItemDialogFragment].
     */
    @set:FragmentArg("itemId")
    var itemId: String? = null
    /**
     * List of [Disposable] to store active subscriptions to [io.reactivex.Observable]s
     */
    private val disposables = ArrayList<Disposable>()

    init {
        // Trigger dependency injection
        AwsomeApp.awsomeComponent?.inject(this)
    }

    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        // Set the style of this [DialogFragment]
        dialog.window?.attributes?.windowAnimations = R.style.AlertDialogAnimation
    }

    /**
     * Gets called after the view binding via Android Annotation is completed.
     * Can be used to bind values from the view model to the views of this layout.
     *
     * In this case it gets also used to configure the view model with the ID of the item to be used.
     */
    @AfterViews
    fun afterViews() {
        itemViewModel.id = itemId
        dialog.setCanceledOnTouchOutside(true)
        disposables.add(itemViewModel.title.subscribe(editText::setText))
    }

    /**
     * Gets called when the submit button gets clicked.
     * Values will be persisted and the view terminated.
     */
    @Click(R.id.ok)
    fun okClicked() {
        itemViewModel.updateItem(editText.text.toString())
        dismiss()
    }

    /**
     * Gets called when the delete button gets clicked.
     * Values will be deleted and the view terminated.
     */
    @Click(R.id.delete)
    fun deleteClicked() {
        itemViewModel.deleteItem()
        dismiss()
    }

    /**
     * Unsubscribe from all active subscriptions of [io.reactivex.Observable]s and clear the list
     * of active subscriptions.
     */
    override fun onDestroy() {
        disposables.forEach(Disposable::dispose)
        disposables.clear()
        super.onDestroy()
    }


}