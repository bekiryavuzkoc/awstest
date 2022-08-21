package com.ui.fragments.basefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    protected lateinit var binding: B
    protected abstract fun initUserInterface(view: View)
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateView(inflater, container)
    }

    open fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserInterface(view)
        setObservers()
    }

    open fun setObservers() {

    }
}