package ie.dylangore.mad1.assignment1.views

import com.github.ajalt.mordant.TermColors
import ie.dylangore.mad1.assignment1.helpers.InputHelper.getIntInput
import ie.dylangore.mad1.assignment1.helpers.TerminalHelper.clearTerminal
import ie.dylangore.mad1.assignment1.helpers.TimeHelper
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.emojiClock
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.emojiCloud
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.emojiHumidity
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.emojiPressure
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.emojiTemp
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.emojiWeather
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.emojiWind
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.getWeatherIcon
import ie.dylangore.mad1.assignment1.helpers.UnicodeHelper.symbolDegree
import ie.dylangore.mad1.assignment1.locationsView
import ie.dylangore.mad1.assignment1.models.Warning
import ie.dylangore.mad1.assignment1.selectedLocation
import ie.dylangore.mad1.assignment1.weather.Forecasts
import ie.dylangore.mad1.assignment1.weather.Stations
import ie.dylangore.mad1.assignment1.weather.Warnings

/**
 * View class for the main menu
 */
class MenuView {

    /**
     * Display the main menu
     *
     * @return the option selected by the user
     */
    fun menu(): Int {
        // Display the menu (With colour in supported environments)
        with(TermColors()) {
            println()
            println((bold + brightBlue)("$emojiWeather Weather Info"))
            locationsView.displaySelectedLocation()
            println((red + underline)("Main Menu"))
            println("1. List Weather Warnings")
            println("2. List Observation Stations")
            println("3. Get Weather Forecast")
            println("4. Manage Locations")
            println()
            println("0. Exit")
            println()
        }

        // Get the input from the user
        return getIntInput()
    }

    /**
     * Display a list of current weather warnings
     */
    fun displayWeatherWarnings(warningsList: List<Warning.WarningItem> = listOf()) {
        clearTerminal()
        // Met Éireann Weather Warnings
        val warnings = if (warningsList.isNullOrEmpty()) Warnings.getWeatherWarnings() else warningsList
        if (warnings.isNotEmpty()) {
            println("Weather Warnings (${warnings.size})")
            for (item in warnings) {
                // Display the weather warnings in a color that matches the warning level
                with(TermColors()){
                    when(item.level.toLowerCase()){
                        "red" -> println(brightRed(item.headline))
                        "orange" -> println(yellow(item.headline))
                        "yellow" -> println(brightYellow(item.headline))
                        else -> println(item.headline)
                    }
                }
            }
        } else {
            println("There are currently no active weather warnings/advisories")
        }
    }

    /**
     * Display a list of observation stations with the latest conditions recorded at each
     */
    fun displayObservationStations() {
        clearTerminal()
        // Met Éireann Observation Stations
        val stations = Stations.getMetEireannStations()
        if (stations != null) {
            println("Met Éireann Observation Stations (${stations.size})")
            for (item in stations) {
                val timestamp:String = TimeHelper.getDisplayString(TimeHelper.fromISO8601(item.time))
                val temperature: String = if (item.temperature != null) item.temperature.toString() else "N/A"
                val humidity: String = if (item.humidity != null) item.humidity.toString() else "N/A"
                val pressure: String = if (item.pressure != null) item.pressure.toString() else "N/A"
                val wind: String = if (item.wind_speed != null) item.wind_speed.toString() else "N/A"
                with(TermColors()) {
                    println("${bold(item.location)} - $emojiClock ${red(" Time: $timestamp")} $emojiTemp ${red(" Temp: $temperature ${symbolDegree}C")} $emojiHumidity ${green(" Hum: $humidity%")}  ${emojiPressure}${blue(" Pres: $pressure hPa")} ${emojiWind}${white(" Wind: $wind km/h")}")
                }
            }
        } else {
            println("No Observation Stations Found!")
        }
    }

    /**
     * Display the weather forecast for the currently selected location
     */
    fun displayWeatherForecast() {
        clearTerminal()
        // Met.no Weather Forecast
        if (selectedLocation != null) {
            val forecast = Forecasts.getForecast(selectedLocation!!.latitude, selectedLocation!!.longitude)
            println("Forecast for ${selectedLocation!!.name}:")
            if (forecast != null) {
                for (entry in forecast.properties.timeseries){
                    val conditions: String = getWeatherIcon(entry.data.next1_Hours?.summary?.symbolCode)
                    val time: String = TimeHelper.getDisplayStringTZ(TimeHelper.fromISO8601(entry.time))
                    val pressure = "$emojiPressure Pres: ${entry.data.instant.details.airPressureAtSeaLevel} ${forecast.properties.meta.units.airPressureAtSeaLevel}"
                    val temperature = "$emojiTemp Temp: ${entry.data.instant.details.airTemperature} ${forecast.properties.meta.units.airTemperature}"
                    val humidity = "$emojiHumidity Hum: ${entry.data.instant.details.relativeHumidity} ${forecast.properties.meta.units.relativeHumidity}"
                    val windDir = "${entry.data.instant.details.windFromDirection} ${forecast.properties.meta.units.windFromDirection}"
                    val windSpeed = "$emojiWind Wind: ${entry.data.instant.details.windSpeed} ${forecast.properties.meta.units.windSpeed}"
                    val clouds = "$emojiCloud Cloud: ${entry.data.instant.details.cloudAreaFraction} ${forecast.properties.meta.units.cloudAreaFraction}"
                    println("$time - $conditions [$temperature $pressure $humidity $windSpeed ($windDir) $clouds] ")
                }
            }else{
                println("Unable to get a weather forecast for the selected location")
            }
        } else {
            print("No location selected!")
        }
    }
}