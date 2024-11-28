package com.serhatuludag.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.serhatuludag.kotlincountries.databinding.FragmentInfoBinding
import com.serhatuludag.kotlincountries.viewmodel.CountryViewModel


class InfoFragment : Fragment() {

    private lateinit var viewModel : CountryViewModel

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private var  countryUuid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        viewModel.getDataFromRoom()

        arguments?.let {
            countryUuid = InfoFragmentArgs.fromBundle(it).countryUuid
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner) { country ->
            country?.let {
                binding.countryName.text = country.countryName
                binding.capitalName.text = country.countryCapital
                binding.regionName.text = country.countryRegion
                binding.cureency.text = country.countryCurrency
                binding.language.text = country.countryLanguage
            }
        }
    }
}