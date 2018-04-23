package com.mhp.superawsometaskapp.viewmodel

import com.mhp.superawsometaskapp.model.ListItem
import com.mhp.superawsometaskapp.service.ListItemService
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

/**
 * View model for a view that creates new instances of [ListItem]. Uses dependency injection to get
 * an instance of [ListItemService] to handle persistence.
 */
class CreateItemViewModel @Inject constructor(private val listItemService: ListItemService) {
    val title = BehaviorSubject.create<String>()!!

    /**
     * Create a new instance of [ListItem] with a given name
     */
    fun createItem(title: String) {
        listItemService.addListItem(ListItem(UUID.randomUUID().toString(), title))
    }

}