package ie.dylangore.mad1.assignment1.views

import com.github.ajalt.mordant.TermColors
import ie.dylangore.mad1.assignment1.helpers.InputHelper.getIntInput
import ie.dylangore.mad1.assignment1.helpers.TerminalHelper.clearTerminal
import ie.dylangore.mad1.assignment1.models.Warning
import ie.dylangore.mad1.assignment1.selectedLocation
import ie.dylangore.mad1.assignment1.weather.Forecasts
import ie.dylangore.mad1.assignment1.weather.Stations
import ie.dylangore.mad1.assignment1.weather.Warnings
import java.text.SimpleDateFormat

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
            println((bold + brightBlue)(String(Character.toChars(0x26C5)) + " Weather Info"))
            val titleStyle = (red + underline)
            println(titleStyle("Main Menu"))
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
        // The timestamp format stored with each station
        val timestampFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        // The format to display each timstamp in
        val displayFormat = SimpleDateFormat("HH:mm dd/MM/yyyy")
        if (stations != null) {
            println("Met Éireann Observation Stations (${stations.size})")
            for (item in stations) {
                val timestamp:String = displayFormat.format(timestampFormat.parse(item.time)).toString()
                val temperature: String = if (item.temperature != null) item.temperature.toString() else "N/A"
                val humidity: String = if (item.humidity != null) item.humidity.toString() else "N/A"
                val pressure: String = if (item.pressure != null) item.pressure.toString() else "N/A"
                val wind: String = if (item.wind_speed != null) item.wind_speed.toString() else "N/A"
                with(TermColors()) {
                    println("${bold(item.location)} - ${String(Character.toChars(0x1F550))}${red(" Time: $timestamp")} ${String(Character.toChars(0x1F321))}${red(" Temp: $temperature °C")}  ${
                        String(Character.toChars(0x1F4A7))
                    }${green(" Hum: $humidity%")}  ${String(Character.toChars(0x2652))}${blue(" Pres: $pressure hPa")} ${
                        String(Character.toChars(0x1F300))
                    }${white(" Wind: $wind km/h")}")
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
            println("Forecast for ${selectedLocation!!.name}: $forecast")
        } else {
            print("No location selected!")
        }
    }


}