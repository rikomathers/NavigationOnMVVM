package com.example.navigationonmvvm.screens.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationonmvvm.databinding.FragmentHelloBinding
import com.example.navigationonmvvm.screens.base.BaseFragment
import com.example.navigationonmvvm.screens.base.BaseScreen
import com.example.navigationonmvvm.screens.base.screenViewModel
import kotlinx.android.parcel.Parcelize

class HelloFragment : BaseFragment() {

    override val viewModel: HelloViewModel by screenViewModel()

    @Parcelize
    class Screen : BaseScreen()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHelloBinding.inflate(inflater, container, false)
        binding.editButton.setOnClickListener {
            viewModel.onEditPressed()
        }
        viewModel.currentMessageLiveData.observe(viewLifecycleOwner) {
            binding.valueTextView.text = it
        }
        return binding.root
    }
}