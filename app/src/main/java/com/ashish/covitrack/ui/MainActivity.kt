package com.ashish.covitrack.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.ashish.covitrack.R
import com.ashish.covitrack.models.VirusModel
import com.ashish.covitrack.network.ApiServices
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var listData : ArrayList<VirusModel> = ArrayList()
    private var totalActive:TextView?=null
    private var totalConfirmed:TextView?=null
    private var todayConfirmed:TextView?=null
    private var totalDeath:TextView?=null
    private var todayDeath:TextView?=null
    private var totalRecovered:TextView?=null
    private var todayRecovereds:TextView?=null
    private var totalTests:TextView?=null
    private var country:TextView?=null
    private var update:TextView?=null
    private var pieChart:PieChart?=null
    private var countryName:String="Nepal"
    private var totalPopulation:TextView?=null
    private var aboutUs:Button?=null
    lateinit var mAdView : AdView
    private val mAppUnitId: String by lazy {

        "ca-app-pub-7906083922186381~5303056044"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(intent.getStringExtra("country")!=null){
            countryName= intent.getStringExtra("country")!!
        }
        init()


        country?.text = countryName
        country?.setOnClickListener{
            startActivity(Intent(this,CountryActivity::class.java))
    }
        aboutUs?.setOnClickListener {
            startActivity(Intent(this,AboutUs::class.java))
        }

        getCountryData()

    }

    private fun getCountryData() {
        ApiServices.virusInstance.getData()
            .enqueue(object:Callback<MutableList<VirusModel>>{
                override fun onResponse(call: Call<MutableList<VirusModel>>, response: Response<MutableList<VirusModel>>) {
                    val data=response.body()
                    if (data != null) {
                        Log.d("Response",data.toString())
                        listData.addAll(data)
                        for(i in listData.indices){
                            if (listData[i].country == countryName){
                                val cases: Int =listData[i].cases
                                val todayCases:Int=listData[i].todayCases
                                val deaths:Int=listData[i].deaths
                                val todayDeaths:Int=listData[i].todayDeaths
                                val active:Int=listData[i].active
                                val recovered:Int=listData[i].recovered
                                val todayRecovered:Int=listData[i].todayRecovered
                                val tests:Int=listData[i].tests
                                val population:Int=listData[i].population

                                totalConfirmed?.text = NumberFormat.getInstance().format(cases)
                                todayConfirmed?.text="+("+NumberFormat.getInstance().format(todayCases)+")"
                                todayDeath?.text="+("+NumberFormat.getInstance().format(todayDeaths)+")"
                                totalDeath?.text=NumberFormat.getInstance().format(deaths)
                                totalActive?.text=NumberFormat.getInstance().format(active)
                                totalRecovered?.text=NumberFormat.getInstance().format(recovered)
                                todayRecovereds?.text="+("+NumberFormat.getInstance().format(todayRecovered)+")"
                                totalTests?.text=NumberFormat.getInstance().format(tests)
                                totalPopulation?.text=NumberFormat.getInstance().format(population)

                                pieChart?.addPieSlice(PieModel("cases",cases.toFloat(),resources.getColor(R.color.yellow)))
                                pieChart?.addPieSlice(PieModel("active",active.toFloat(),resources.getColor(R.color.blue)))
                                pieChart?.addPieSlice(PieModel("recovered",recovered.toFloat(),resources.getColor(R.color.green)))
                                pieChart?.addPieSlice(PieModel("deaths",deaths.toFloat(),resources.getColor(R.color.red)))
                                pieChart?.startAnimation()
                                setText(listData[i].updated)


                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<VirusModel>>, t: Throwable) {
                    Log.d("Error",t.message.toString())
                }

            })
    }

    private fun setText(updated: Long) {
        val format:DateFormat=SimpleDateFormat("dd MMM,yyyy")
        val calendar:Calendar= Calendar.getInstance()
        calendar.timeInMillis = updated
        update?.text="Updated at "+format.format(calendar.time)


    }

    private fun init(){
        totalActive=findViewById(R.id.totalActive)
        totalConfirmed=findViewById(R.id.totalConfirm)
        todayConfirmed=findViewById(R.id.todayConfirm)
        totalDeath=findViewById(R.id.totalDeath)
        todayDeath=findViewById(R.id.todayDeath)
        totalRecovered=findViewById(R.id.totalRecovered)
        todayRecovereds=findViewById(R.id.todayRecovered)
        totalTests=findViewById(R.id.totalTest)
        country=findViewById(R.id.country)
        update=findViewById(R.id.updateDate)
        pieChart=findViewById((R.id.pieChart))
        totalPopulation=findViewById(R.id.totalPopulation)
        aboutUs=findViewById(R.id.aboutUs)
    }

}