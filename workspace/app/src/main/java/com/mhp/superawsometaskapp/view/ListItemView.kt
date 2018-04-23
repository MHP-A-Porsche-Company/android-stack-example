package com.mhp.superawsometaskapp.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import com.mhp.superawsometaskapp.R
import com.mhp.superawsometaskapp.model.ListItem
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

/**
 * Custom implementation of [RelativeLayout] to display a list entry for a given [ListItem].
 */
@EViewGroup(R.layout.view_list_item)
open class ListItemView : RelativeLayout {

    /**
     * The [TextView] to display the name of the [ListItem]
     */
    @ViewById(R.id.text)
    lateinit var textView: TextView

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    /**
     * The instance of [ListItem] that is supposed to be displayed by this [android.view.View]
     */
    var listItem: ListItem? = null
        set(value) {
            field = value
            afterViews()
        }

    /**
     * Gets called after the view binding via Android Annotation is completed.
     * Can be used to bind values from the view model to the views of this layout.
     */
    @AfterViews
    fun afterViews() {
        textView.text = listItem?.title
    }
}