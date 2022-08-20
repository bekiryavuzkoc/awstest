package com.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.amazontest.R
import com.example.amazontest.databinding.FragmentOperationsBinding
import com.ui.fragments.basefragment.BaseFragment
import com.viewmodel.OperationsViewModel
import com.viewmodel.event.EventObserver

class OperationsFragment : BaseFragment<FragmentOperationsBinding>() {

    private val viewModel: OperationsViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_operations

    override fun initUserInterface(view: View) {
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.createUserButton.setOnClickListener {
            viewModel.create(binding.nameEditText.toString())
            binding.nameEditText.text?.clear()
        }

        binding.readSpecificUserButton.setOnClickListener {
            viewModel.create(binding.nameEditText.toString())
            binding.nameEditText.text?.clear()
        }

        binding.updateUserButton.setOnClickListener {
            viewModel.create(binding.nameEditText.toString())
            binding.nameEditText.text?.clear()
        }

        binding.deleteUserButton.setOnClickListener {
            viewModel.create(binding.nameEditText.toString())
            binding.nameEditText.text?.clear()
        }

        binding.readAllUsers.setOnClickListener {
            viewModel.create(binding.nameEditText.toString())
            binding.nameEditText.text?.clear()
        }
    }


    override fun setObservers() {
        viewModel.toastText.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}