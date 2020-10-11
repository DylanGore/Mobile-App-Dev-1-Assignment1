package ie.dylangore.mad1.assignment1.ui

import ie.dylangore.mad1.assignment1.logger
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*

class MainView : View("Weather") {

    var weatherOverview = SimpleStringProperty("Default")
    val mainController: MainController by inject()

    override val root = vbox {
        spacing = 10.0
        alignment = Pos.CENTER
        label {
            bind(weatherOverview)
        }
        hbox {
            alignment = Pos.BASELINE_CENTER
            button("Update Weather Data") {
                action { logger.info("Update weather data")
                    weatherOverview.set("Weather Info")
                }
            }
            button("Manage Places") {
                action { logger.info("Manage places") }
            }
        }
    }
}






