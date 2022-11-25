package com.sopt.aladinaos.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.aladinaos.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { }
    private lateinit var editorAdapter: HomeEditorChoiceAdapter
    private lateinit var topSellerAdapter: HomeTopsellerAdapter

    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

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

        binding.lifecycleOwner = viewLifecycleOwner
        homeViewModel.getPickData()
        editorAdapter = HomeEditorChoiceAdapter(requireContext())
        topSellerAdapter = HomeTopsellerAdapter(requireContext())
        binding.rvHomeEditor.adapter = editorAdapter
        binding.rvHomeTopseller.adapter = topSellerAdapter
        homeViewModel.editorPickResult.observe(viewLifecycleOwner) {
            editorAdapter.setPickList(it.toList())
        }
        homeViewModel.topSellerResult.observe(viewLifecycleOwner) {
            topSellerAdapter.setTopicList(it.toList())
        }
    }
}
