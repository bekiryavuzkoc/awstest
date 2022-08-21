package com.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.amazontest.R
import com.example.amazontest.databinding.FragmentOperationsBinding
import com.ui.fragments.basefragment.BaseFragment
import com.viewmodel.OperationsViewModel
import com.viewmodel.event.EventObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OperationsFragment : BaseFragment<FragmentOperationsBinding>() {
    override fun getLayoutId() = R.layout.fragment_operations
    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    val viewModel: OperationsViewModel by viewModels()

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    override fun initUserInterface(view: View) {
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.createUserButton.setOnClickListener {
            viewModel.create(binding.nameEditText.text.toString())
            binding.nameEditText.text?.clear()
        }

        binding.readSpecificUserButton.setOnClickListener {
            uiScope.launch(Dispatchers.IO) {
                viewModel.readSpecificID(binding.idEditText.text.toString())
            }
            binding.idEditText.text?.clear()
        }

        binding.updateUserButton.setOnClickListener {
            viewModel.updateSpecificID(
                binding.idEditText.text.toString(),
                binding.updateUserAgeEdittext.text.toString()
            )
            binding.updateUserAgeEdittext.text?.clear()
        }

        binding.deleteUserButton.setOnClickListener {
            viewModel.deleteUser(binding.idDeleteUser.text.toString())
            binding.idDeleteUser.text?.clear()
        }

        binding.readAllUsers.setOnClickListener {
            viewModel.readAll()
        }
    }
    override fun setObservers() {
        viewModel.toastText.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}