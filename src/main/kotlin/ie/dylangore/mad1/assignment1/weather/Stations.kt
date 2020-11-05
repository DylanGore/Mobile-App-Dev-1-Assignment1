package ie.dylangore.mad1.assignment1.weather

import com.beust.klaxon.Klaxon
import ie.dylangore.mad1.assignment1.datamodels.Forecast
import ie.dylangore.mad1.assignment1.datamodels.ObservationStation
import ie.dylangore.mad1.assignment1.datamodels.Warning
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException

/**
 * Object that handles getting and formatting forecast data
 */
object Stations {
    // Create the HTTP client used to communicate with the API
    private val client = OkHttpClient()

    /**
     * Make a HTTP request to the STREAM maps API
     *
     * @param baseUrl the API base URL
     * @param stationType the type of observation station to return
     * @return the station data as a JSON string
     */
    private fun apiRequest(baseUrl: String = "https://maps.stream.dylangore.space/api/latest", stationType: String = "meteireann"): String {
        val request = Request.Builder().url("$baseUrl/$stationType").build()
        var result : String

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            result = response.body!!.string()
        }

        return(result)
    }

    /**
     * Convert the JSON from STREAM to the a list of objects
     *
     * @param data the forecast data as a JSON string
     * @return the forecast data as a Forecast object
     */
    fun getMetEireannStations(data: String = apiRequest()): List<ObservationStation.ObservationStationItem>? {
        return (Klaxon().parseArray(data))
    }
}