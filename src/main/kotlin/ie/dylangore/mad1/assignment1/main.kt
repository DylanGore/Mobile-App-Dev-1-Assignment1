package ie.dylangore.mad1.assignment1

import ie.dylangore.mad1.assignment1.weather.Forecasts
import ie.dylangore.mad1.assignment1.weather.Warnings

/**
 * Main entrypoint for the console version of the application
 *
 * @param args
 */
fun main(args: Array<String>) {
    logger.info("Launching Console-Only mode")

    val warnings = Warnings.getWeatherWarnings()
    println("""Weather Warnings (${warnings.size})""")
    for ( item in warnings ){
        println(item.headline)
    }
    val forecast = Forecasts.getForecast()
    println("Forecast: $forecast")
}