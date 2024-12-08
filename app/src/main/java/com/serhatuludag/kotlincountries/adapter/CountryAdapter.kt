package com.serhatuludag.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.serhatuludag.kotlincountries.databinding.ItemRowBinding
import com.serhatuludag.kotlincountries.model.Country
import com.serhatuludag.kotlincountries.util.downloadFromURL
import com.serhatuludag.kotlincountries.util.placeHolderProgressBar
import com.serhatuludag.kotlincountries.view.FeedFragmentDirections

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
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToInfoFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageView.downloadFromURL(countryList[position].countryFlag, placeHolderProgressBar(holder.itemView.context))

    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}