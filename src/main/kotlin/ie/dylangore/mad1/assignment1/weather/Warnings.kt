package ie.dylangore.mad1.assignment1.weather

import com.beust.klaxon.Klaxon
import ie.dylangore.mad1.assignment1.models.Warning
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException

/**
 * Object that handles getting and formatting weather warning data from Met Éireann
 */
object Warnings {
    // Create the HTTP client used to communicate with the API
    private val client = OkHttpClient()

    @Suppress("SpellCheckingInspection")
    // Map of region codes to region names
    private val regionMap: Map<String, String> = mapOf(
        "EI01" to "Carlow",
        "EI02" to "Cavan",
        "EI03" to "Clare",
        "EI04" to "Cork",
        "EI06" to "Donegal",
        "EI07" to "Dublin",
        "EI10" to "Galway",
        "EI11" to "Kerry",
        "EI12" to "Kildare",
        "EI13" to "Kilkenny",
        "EI15" to "Laois",
        "EI14" to "Leitrim",
        "EI16" to "Limerick",
        "EI18" to "Longford",
        "EI19" to "Louth",
        "EI20" to "Mayo",
        "EI21" to "Meath",
        "EI22" to "Monaghan",
        "EI23" to "Offaly",
        "EI24" to "Roscommon",
        "EI25" to "Sligo",
        "EI26" to "Tipperary",
        "EI27" to "Waterford",
        "EI29" to "Westmeath",
        "EI30" to "Wexford",
        "EI31" to "Wicklow",
        // Marine
        "EI805" to "Malin-Fair",
        "EI806" to "Fair-Belfast",
        "EI807" to "Belfast-Strang",
        "EI808" to "Strang-Carl",
        "EI809" to "Carling-Howth",
        "EI810" to "Howth-Wicklow",
        "EI811" to "Wicklow-Carns",
        "EI812" to "Carns-Hook",
        "EI813" to "Hook-Dungarvan",
        "EI814" to "Dungarvan-Roches",
        "EI815" to "Roches-Mizen",
        "EI816" to "Mizen-Valentia",
        "EI817" to "Valentia-Loop",
        "EI818" to "Loop-Slayne",
        "EI819" to "Slayne-Ennis",
        "EI820" to "Erris-Rossan",
        "EI821" to "Rossan-BloodyF",
        "EI822" to "BloodyF-Malin",
        "EI823" to "IrishSea-South",
        "EI824" to "IrishSea-IOM-S",
        "EI825" to "IrishSea-IOM-N",
    )

    @Suppress("SpellCheckingInspection")
    // List containing all counties that Met Éireann will provide warnings for, in the order that they generally use
    private val allCounties: List<String> = listOf("Carlow", "Dublin", "Kildare", "Kilkenny", "Laois", "Longford", "Louth",
        "Meath", "Offaly", "Westmeath", "Wexford", "Wicklow", "Clare", "Cork", "Kerry", "Limerick", "Tipperary",
        "Waterford", "Galway", "Leitrim", "Mayo", "Roscommon", "Sligo", "Cavan", "Donegal", "Monaghan")

    /**
     * Make a HTTP request to the Met Éireann Weather Warning API to get
     * all current weather warnings in JSON format
     *
     * @param url the URL of the weather warning API
     * @return the JSON string of data
     */
    private fun apiRequest(url: String = "https://www.met.ie/Open_Data/json/warning_IRELAND.json"): String {
        val request = Request.Builder().url(url).build()
        var result : String

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            result = response.body!!.string()
        }
        return(result)
    }

    /**
     * Function to parse the JSON data provided by the API and return it as a list of WarningItems
     *
     * @param data the JSON data provided by the weather warning API
     * @return the list of WarningItems
     */
    fun getWeatherWarnings(data: String = apiRequest()): List<Warning.WarningItem> {
        val warnings: List<Warning.WarningItem> = Klaxon().parseArray(data)!!
        // Loop through each entry and replce the region FIPS/EMMA ID code with the region name
        for (item in warnings){
            val regionList: List<String> = item.regions // Existing list
            val newRegionList: MutableList<String> = mutableListOf()
            // Loop through each existing region and add it's name to the new list
            for (region in regionList){
                if (regionMap.containsKey(region)){
                    newRegionList.add(regionMap.get(region).toString())
                } else {
                    // Add the code if it isn't found in the map
                    newRegionList.add(region)
                }
            }

            // If list contains all 26 counties, replace them with one value to represent the entire country
            if (newRegionList.containsAll(allCounties)){
                for ( county in allCounties ){
                    newRegionList.remove(county)
                }
                newRegionList.add("Ireland")
            }

            // Set the regions for this warning to the new list
            item.regions = newRegionList
        }
        return(warnings)
    }
}