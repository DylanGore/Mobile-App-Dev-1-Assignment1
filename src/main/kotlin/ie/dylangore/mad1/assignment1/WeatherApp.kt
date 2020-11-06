package ie.dylangore.mad1.assignment1

import ie.dylangore.mad1.assignment1.ui.MainView
import ie.dylangore.mad1.assignment1.ui.Styles
import javafx.stage.Stage
import mu.KotlinLogging
import tornadofx.App
import tornadofx.launch

val logger = KotlinLogging.logger {}

/**
 * Main entrypoint for the GUI version of the app
 *
 */
class WeatherApp: App(MainView::class, Styles::class){
    override fun start(stage: Stage) {
        stage.isResizable = true
        stage.minWidth = 200.0
        stage.minHeight = 300.0
        super.start(stage)
    }
}

/**
 * Main function that launches the GUI
 *
 * @param args
 */
fun main(args: Array<String>) {
    logger.info { "Mobile App Dev 1 Assignment 1" }
    launch<WeatherApp>()
}