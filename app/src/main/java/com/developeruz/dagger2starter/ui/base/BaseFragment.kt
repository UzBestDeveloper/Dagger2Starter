package com.developeruz.dagger2starter.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {

    val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        setup()
        clicks()
    }

    open fun observe() {}

    open fun setup() {}

    open fun clicks() {}

    fun back() = navController.popBackStack()


}