package com.mhp.superawsometaskapp.viewmodel

import io.reactivex.subjects.BehaviorSubject

interface EditItemViewModel {
    val title: BehaviorSubject<String>
    /**
     * The ID of the [ListItem] to be updated
     */
    var id: String?

    /**
     * Update the instance of [ListItem] with the given value.
     */
    fun updateItem(title: String)

    /**
     * Delete the current instance of [ListItem] from the persistence.
     */
    fun deleteItem()
}