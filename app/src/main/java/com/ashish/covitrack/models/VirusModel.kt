package com.ashish.covitrack.models

data class VirusModel(
	val country: String,
	val cases: Int,
    val active:Int,
	val recovered: Int,
	val tests: Int,
	val todayRecovered: Int,
	val updated: Long,
	val deaths: Int,
	val todayCases: Int,
	val todayDeaths: Int,
	val population:Int,
	val countryInfo: Map<String, String>
)

