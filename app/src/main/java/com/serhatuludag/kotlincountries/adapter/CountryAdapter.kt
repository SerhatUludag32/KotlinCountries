package com.serhatuludag.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serhatuludag.kotlincountries.R
import com.serhatuludag.kotlincountries.databinding.ItemRowBinding
import com.serhatuludag.kotlincountries.model.Country

class CountryAdapter(val countryList : ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.countryName.text = countryList[position].countryName
        holder.binding.regionName.text = countryList[position].countryRegion
        //Image

    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}