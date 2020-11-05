package ie.dylangore.mad1.assignment1.ui

import ie.dylangore.mad1.assignment1.logger
import ie.dylangore.mad1.assignment1.weather.Warnings
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*

/**
 * Create the main UI view
 *
 */
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
                    val warnings = Warnings.getWeatherWarnings()
                    weatherOverview.set("""Weather Warnings (${warnings.size})""")
                    var currentText = weatherOverview.get();
                    for ( item in warnings ){
                        weatherOverview.set(currentText + "\n\n" + item.headline + "\n" + item.description);
                        currentText = weatherOverview.get();
                    }
                }
            }
            button("Manage Places") {
                action { logger.info("Manage places") }
            }
        }
    }
}






