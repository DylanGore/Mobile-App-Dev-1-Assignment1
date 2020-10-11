package ie.dylangore.mad1.assignment1.weather

import com.beust.klaxon.Klaxon
import ie.dylangore.mad1.assignment1.datamodels.Warning
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException

object Warnings {
    private val client = OkHttpClient()

    private fun apiRequest(url: String = "https://www.met.ie/Open_Data/json/warning_IRELAND.json"): String {
        val request = Request.Builder().url(url).build()
        var result : String

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            result = response.body!!.string()
        }
        return(result)
    }
    fun getWeatherWarnings(data: String = apiRequest()): List<Warning.WarningItem> {
        return(Klaxon().parseArray(data)!!)
    }
}