package com.ashish.covitrack.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashish.covitrack.R
import com.ashish.covitrack.models.VirusModel
import com.bumptech.glide.Glide

class CountryAdapter(private var virus:List<VirusModel>, private val context: Context):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.country_list,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val virusPosition=virus[position]
        holder.countryName.text=virusPosition.country
        holder.totalCases.text=virusPosition.cases.toString()
        val img:Map<String,String> = virusPosition.countryInfo
        Glide.with(context).load(img["flag"]).into(holder.flagImage)

        holder.itemView.setOnClickListener {
            val intent=Intent(context,MainActivity::class.java)
            intent.putExtra("country",virusPosition.country)
            context.startActivity(intent)
        }
    }

    fun filterList(filterList : List<VirusModel>){
        virus=filterList
        notifyDataSetChanged()
    }

    override fun getItemCount()=virus.size

    class CountryViewHolder(view:View):RecyclerView.ViewHolder(view){

        var countryName:TextView=view.findViewById(R.id.countryName)
        var totalCases:TextView=view.findViewById(R.id.totalCases)
        var flagImage:ImageView=view.findViewById(R.id.flagImage)

    }

}