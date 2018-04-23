package com.mhp.superawsometaskapp.injection

import com.mhp.superawsometaskapp.adapter.ListItemAdapter
import com.mhp.superawsometaskapp.fragment.CreateItemDialogFragment
import com.mhp.superawsometaskapp.fragment.EditItemDialogFragment
import com.mhp.superawsometaskapp.fragment.ListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Component that gets used by Dagger Dependency Injection. Implementation of this interface gets
 * generated at compile time and will be used by the calling classes at run time to trigger the
 * injection of their declared dependencies.
 */
@Singleton
@Component(modules = [(AwsomeModule::class)])
interface AwsomeComponent {
    fun inject(listItemAdapter: ListItemAdapter)

    fun inject(listItemAdapter: ListFragment)

    fun inject(editItemDialogFragment: EditItemDialogFragment)

    fun inject(createItemDialogFragment: CreateItemDialogFragment)
}