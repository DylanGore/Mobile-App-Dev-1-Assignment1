package ie.dylangore.mad1.assignment1

import com.github.ajalt.mordant.TermColors
import ie.dylangore.mad1.assignment1.weather.Forecasts
import ie.dylangore.mad1.assignment1.weather.Stations
import ie.dylangore.mad1.assignment1.weather.Warnings

/**
 * Main entrypoint for the console version of the application
 *
 * @param args
 */
fun main(args: Array<String>) {
    logger.info("Launching Console-Only mode")

    var input: Int = menu()

    do {
        input = menu()
        when(input) {
            1 -> displayWeatherWarnings()
            2 -> displayObservationStations()
            3 -> displayWeatherForecast()
            4 -> displayManagePlacesMenu()
            0 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != 0)
}

fun menu() : Int {
    var option : Int
    var input: String? = null

    // Display the menu (With colour in supported environments)
    with(TermColors()) {
        println()
        println((bold+brightBlue)(String(Character.toChars(0x26C5)) + " Weather Info"))
        val titleStyle = (red + underline)
        println(titleStyle("Main Menu"))
        println(" 1. List Weather Warnings")
        println(" 2. List Observation Stations")
        println(" 3. Get Weather Forecast")
        println(" 4. Manage Places")

        println(" 0. Exit")
        println()
        print("Select an option : ")
        println()
    }

    // Store the input String
    input = readLine()!!
    // Simple input validation and convert to Integer option
    option = if (input.toIntOrNull() != null && input.isNotEmpty()) input.toInt() else 0
    return option
}

fun displayWeatherWarnings(){
    // Met Éireann Weather Warnings
    val warnings = Warnings.getWeatherWarnings()
    if (warnings.isNotEmpty()){
        println("""Weather Warnings (${warnings.size})""")
        for ( item in warnings ){
            println(item.headline)
        }
    }else {
        println("There are currently no active weather warnings/advisories")
    }
}

fun displayObservationStations(){
    // Met Éireann Observation Stations
    val stations = Stations.getMetEireannStations()
    println("""Met Éireann Observation Stations (${stations?.size})""")
    for ( item in stations!!){
        val temperature : String = if (item.temperature != null) item.temperature.toString() else "N/A"
        val humidity : String = if (item.humidity != null) item.humidity.toString() else "N/A"
        val pressure : String = if (item.pressure != null) item.pressure.toString() else "N/A"
        val wind : String = if (item.wind_speed != null) item.wind_speed.toString() else "N/A"
        with(TermColors()) {
            println("${bold(item.location)} - ${String(Character.toChars(0x1F321))}${red(" Temp: $temperature °C")}  ${String(Character.toChars(0x1F4A7))}${green(" Hum: $humidity%")}  ${String(Character.toChars(0x2652))}${blue(" Pres: $pressure hPa")} ${String(Character.toChars(0x1F300))}${white(" Wind: $wind km/h")}")
        }
    }
}

fun displayWeatherForecast(){
    // Met.no Weather Forecast
    val forecast = Forecasts.getForecast()
    println("Forecast: $forecast")
}

fun displayManagePlacesMenu(){
    println("Not yet implemented")
}