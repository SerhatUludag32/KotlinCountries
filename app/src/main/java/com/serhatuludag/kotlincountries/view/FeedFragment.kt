package com.serhatuludag.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhatuludag.kotlincountries.adapter.CountryAdapter
import com.serhatuludag.kotlincountries.databinding.FragmentFeedBinding
import com.serhatuludag.kotlincountries.viewmodel.FeedViewModel


class FeedFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.adapter = countryAdapter



        /*
        binding.fragmentbutton.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToInfoFragment()
            Navigation.findNavController(it).navigate(action)
        }
        */

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        }
        viewModel.countryError.observe(viewLifecycleOwner) { error ->
            error?.let {
                if (it) {
                    binding.countryError.visibility = View.VISIBLE
                    binding.countryLoading.visibility = View.GONE
                    binding.countryList.visibility = View.GONE
                } else {
                    binding.countryError.visibility = View.GONE
                }
            }
        }
        viewModel.countryLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                    binding.countryError.visibility = View.GONE
                } else {
                    binding.countryLoading.visibility = View.GONE
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}