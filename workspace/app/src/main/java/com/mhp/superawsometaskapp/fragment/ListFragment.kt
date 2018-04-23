package com.mhp.superawsometaskapp.fragment

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mhp.AwsomeApp
import com.mhp.superawsometaskapp.R
import com.mhp.superawsometaskapp.adapter.ListItemAdapter
import com.mhp.superawsometaskapp.view.ListItemView
import com.mhp.superawsometaskapp.viewmodel.ListViewModel
import io.reactivex.disposables.Disposable
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

/**
 * Implementation of [Fragment] to display a list of [com.mhp.superawsometaskapp.model.ListItem]s
 * inside a [RecyclerView]. A custom implementation of [RecyclerView.Adapter] gets used to display
 * the current values. A view model gets injected at runtime to take care of the business logic.
 */
@EFragment(R.layout.fragment_list)
open class ListFragment : Fragment() {
    /**
     * The recycler view to display the current set of [com.mhp.superawsometaskapp.model.ListItem]s.
     */
    @ViewById(R.id.items)
    lateinit var recyclerView: RecyclerView
    /**
     * The view model that holds the business logic
     */
    @Inject
    lateinit var listViewModel: ListViewModel
    /**
     * List of [Disposable] to store active subscriptions to [io.reactivex.Observable]s
     */
    private val disposables = ArrayList<Disposable>()

    init {
        // Trigger dependency injection
        AwsomeApp.awsomeComponent?.inject(this)
    }

    /**
     * Gets called after the view binding via Android Annotation is completed.
     * Can be used to bind values from the view model to the views of this layout.
     */
    @AfterViews
    fun afterViews() {
        context?.let { context ->
            recyclerView.adapter = ListItemAdapter(View.OnClickListener {
                val item = (it as ListItemView).listItem
                EditItemDialogFragment_.builder().setItemId(item?.id).build().show(fragmentManager, "EditItemFragment")
            }, context)

        }
        disposables.add(listViewModel.items.subscribe { items ->
            (recyclerView.adapter as ListItemAdapter).updateList(items)
        })
    }

    /**
     * Gets called when the user clicks the add button. Will result in the appearance of a
     * [CreateItemDialogFragment].
     */
    @Click(R.id.add_button)
    fun addButtonClicked() {
        CreateItemDialogFragment_.builder().build().show(fragmentManager, "EditItemFragment")
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