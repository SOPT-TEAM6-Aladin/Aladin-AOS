package com.sopt.aladinaos.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sopt.aladinaos.R
import com.sopt.aladinaos.databinding.FragmentHomeBinding
import com.sopt.aladinaos.util.binding.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null

    private val editorAdapter = HomeEditorChoiceAdapter()
    private val topSellerAdapter = HomeTopsellerAdapter()


    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHomeEditor.adapter = editorAdapter
        binding.rvHomeTopseller.adapter = topSellerAdapter
        homeViewModel.getPickData()
        binding.lifecycleOwner = viewLifecycleOwner

        homeViewModel.editorPickResult.observe(viewLifecycleOwner) {
            editorAdapter.notifyDataSetChanged()
        }
        homeViewModel.topSellerResult.observe(viewLifecycleOwner) {
            topSellerAdapter.notifyDataSetChanged()
        }
    }
}
