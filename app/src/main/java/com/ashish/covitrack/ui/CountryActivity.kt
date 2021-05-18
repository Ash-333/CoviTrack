package com.ashish.covitrack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashish.covitrack.R
import com.ashish.covitrack.models.VirusModel
import com.ashish.covitrack.network.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryActivity : AppCompatActivity() {
    private var recyclerView:RecyclerView?=null
    private var searchBar:EditText?=null
    private var listData : ArrayList<VirusModel> = ArrayList()
    private lateinit var adapter: CountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        recyclerView=findViewById(R.id.recyclerView)
        searchBar=findViewById(R.id.search)
        adapter= CountryAdapter(listData,this@CountryActivity)
        recyclerView?.adapter=adapter
        recyclerView?.layoutManager= LinearLayoutManager(this@CountryActivity)
        recyclerView?.setHasFixedSize(true)
        getCountryData()
        searchBar?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filter(text.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

    }
    fun filter(text:String){
        var filterLists : ArrayList<VirusModel> = ArrayList()
//        var item:VirusModel
        for (item in listData){
            if (item.country.lowercase().contains(text.toLowerCase())){
                filterLists.add(item)
            }
        }
        adapter.filterList(filterLists)
    }

    private fun getCountryData() {
        ApiServices.virusInstance.getData()
            .enqueue(object: Callback<MutableList<VirusModel>> {
                override fun onResponse(call: Call<MutableList<VirusModel>>, response: Response<MutableList<VirusModel>>) {
                    val data=response.body()
                    if (data != null) {
                        Log.d("ResponseCountryActivity",data.toString())
                        listData.addAll(data)
                        adapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<MutableList<VirusModel>>, t: Throwable) {
                    Log.d("Error",t.message.toString())
                }
            })
    }
}