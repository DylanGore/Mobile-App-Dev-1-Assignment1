package ie.dylangore.mad1.assignment1.weather

import com.beust.klaxon.Klaxon
import ie.dylangore.mad1.assignment1.datamodels.Forecast
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException

/**
 * Object that handles getting and formatting forecast data
 */
object Forecasts {
    // Create the HTTP client used to communicate with the API
    private val client = OkHttpClient()

    /**
     * Make a HTTP request to the Met.No forecast API to get weather forecast data in JSON
     * format for a given location
     *
     * @param baseUrl the API base URL without any arguments
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     * @param altitude the altitude of the location
     * @return the forecast data as a JSON string
     */
    private fun apiRequest(baseUrl: String = "https://api.met.no/weatherapi/locationforecast/2.0/compact", latitude:Double = 52.2461, longitude:Double = -7.1387, altitude: Int = 0): String {
        val request = Request.Builder().url("$baseUrl?altitude=$altitude&lat=$latitude&lon=$longitude").header("User-Agent", "KotlinWeather").build()
        var result : String

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            result = response.body!!.string()
        }

        return(result)
    }

    /**
     * Convert the forecast JSON from Met.No to the Forecast data object
     *
     * @param data the forecast data as a JSON string
     * @return the forecast data as a Forecast object
     */
    fun getForecast(data: String = apiRequest()): Forecast? {
        return (Klaxon().parse(data))
    }
}