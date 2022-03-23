package com.developeruz.dagger2starter.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developeruz.dagger2starter.Application
import com.developeruz.dagger2starter.R
import com.developeruz.dagger2starter.databinding.FragmentMainBinding
import com.developeruz.dagger2starter.databinding.FragmentSignInBinding
import com.developeruz.dagger2starter.ui.base.BaseFragment
import com.developeruz.dagger2starter.ui.fragments.auth.signIn.SignInViewModel
import javax.inject.Inject


class MainFragment : BaseFragment(R.layout.fragment_main) {

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