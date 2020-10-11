package ie.dylangore.mad1.assignment1

import ie.dylangore.mad1.assignment1.weather.Warnings

fun main(args: Array<String>) {
    logger.info("Launching Console-Only mode")

    val warnings = Warnings.getWeatherWarnings()
    println("""Weather Warnings (${warnings.size}) $warnings""")
}