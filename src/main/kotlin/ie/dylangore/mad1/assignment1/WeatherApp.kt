package ie.dylangore.mad1.assignment1

import ie.dylangore.mad1.assignment1.ui.MainView
import ie.dylangore.mad1.assignment1.ui.Styles
import javafx.stage.Stage
import mu.KotlinLogging
import tornadofx.App
import tornadofx.launch

val logger = KotlinLogging.logger {}

class WeatherApp: App(MainView::class, Styles::class){
    override fun start(stage: Stage) {
        stage.isResizable = false
        stage.minWidth = 200.0
        stage.minHeight = 100.0
        super.start(stage)
    }
}

fun main(args: Array<String>) {
    logger.info { "Mobile App Dev 1 Assignment 1" }
    launch<WeatherApp>()
}