package com.example.navigationonmvvm.screens.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationonmvvm.databinding.FragmentEditBinding
import com.example.navigationonmvvm.screens.base.BaseFragment
import com.example.navigationonmvvm.screens.base.BaseScreen
import com.example.navigationonmvvm.screens.base.screenViewModel
import kotlinx.android.parcel.Parcelize

class EditFragment : BaseFragment() {

    override val viewModel:EditViewModel by screenViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        viewModel.initialMessageEvent.observe(viewLifecycleOwner) {
            it.getValue()?.let { message -> binding.valueEditText.setText(message) }
        }
        binding.cancelButton.setOnClickListener {
            viewModel.onCancelPressed()
        }
        binding.saveButton.setOnClickListener {
            viewModel.onSavePressed(binding.valueEditText.text.toString())
        }
        return binding.root
    }

    @Parcelize
    class Screen(
        val initialValue: String
    ) : BaseScreen()
}

