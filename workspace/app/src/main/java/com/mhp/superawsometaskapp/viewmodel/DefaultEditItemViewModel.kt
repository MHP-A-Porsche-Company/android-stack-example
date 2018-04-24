package com.mhp.superawsometaskapp.viewmodel

import com.mhp.superawsometaskapp.model.ListItem
import com.mhp.superawsometaskapp.service.ListItemService
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * View model for a view that updates an existing instance of [ListItem]. Uses dependency injection to get
 * an instance of [ListItemService] to handle persistence.
 */
class DefaultEditItemViewModel @Inject constructor(val listItemService: ListItemService) : EditItemViewModel {
    override val title = BehaviorSubject.create<String>()!!

    /**
     * The ID of the [ListItem] to be updated
     */
    override var id: String? = null
        /**
         *Set the ID [ListItem] and trigger loading of the item from the persistence.
         */
        set(value) {
            field = value
            listItemService.findById(value)?.let { listItem ->
                // Update the values in the UI
                title.onNext(listItem.title)
            }
        }

    /**
     * Update the instance of [ListItem] with the given value.
     */
    override fun updateItem(title: String) {
        id?.let { listItemService.updatedListItem(ListItem(it, title)) }
    }

    /**
     * Delete the current instance of [ListItem] from the persistence.
     */
    override fun deleteItem() {
        id?.let(listItemService::removeListItem)
    }
}