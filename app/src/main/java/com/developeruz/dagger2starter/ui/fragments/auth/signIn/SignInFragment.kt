package com.developeruz.dagger2starter.ui.fragments.auth.signIn

import android.content.Context
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developeruz.dagger2starter.Application
import com.developeruz.dagger2starter.R
import com.developeruz.dagger2starter.databinding.FragmentSignInBinding
import com.developeruz.dagger2starter.ui.base.BaseFragment
import com.developeruz.dagger2starter.utils.toast
import javax.inject.Inject

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    @Inject lateinit var viewModel: SignInViewModel

    private val binding: FragmentSignInBinding by viewBinding()

    override fun setup() {
        viewModel.isUserRegistered("998943296949")
    }

    override fun observe() {
        viewModel.successResponse.observe(viewLifecycleOwner){
            navController.navigate(R.id.action_signInFragment_to_mainFragment)
        }

        viewModel.errorData.observe(viewLifecycleOwner){
            toast(it.message)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as Application).appComponent.fragmentComponent().create()
            .inject(this)
    }

}