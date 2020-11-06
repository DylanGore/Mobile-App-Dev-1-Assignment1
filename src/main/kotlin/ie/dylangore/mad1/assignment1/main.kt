package ie.dylangore.mad1.assignment1

import ie.dylangore.mad1.assignment1.controllers.LocationsController
import ie.dylangore.mad1.assignment1.controllers.MenuController
import ie.dylangore.mad1.assignment1.models.Location
import ie.dylangore.mad1.assignment1.models.storage.LocationMemoryStore
import ie.dylangore.mad1.assignment1.views.LocationsView
import ie.dylangore.mad1.assignment1.views.MenuView

// Define instances of each of the required views and controllers
val locationsView: LocationsView = LocationsView()
val locations = LocationMemoryStore()
val menuView = MenuView()
val locationsController = LocationsController()

// The currently selected location
var selectedLocation: Location? = null

/**
 * Main entrypoint for the console version of the application
 *
 * @param args
 */
fun main(args: Array<String>) {
    // Display the main menu
    MenuController().start()
}