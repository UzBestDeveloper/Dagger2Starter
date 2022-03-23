package com.developeruz.dagger2starter.ui.fragments.main

import android.content.Context
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developeruz.dagger2starter.Application
import com.developeruz.dagger2starter.R
import com.developeruz.dagger2starter.databinding.FragmentMainBinding
import com.developeruz.dagger2starter.ui.base.BaseFragment
import javax.inject.Inject


class MainFragment : BaseFragment(R.layout.fragment_main) {

    @Inject lateinit var viewModel: MainViewModel

    private val binding: FragmentMainBinding by viewBinding()

    override fun setup() {

    }

    override fun observe() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as Application).appComponent.fragmentComponent().create()
            .inject(this)
    }
}