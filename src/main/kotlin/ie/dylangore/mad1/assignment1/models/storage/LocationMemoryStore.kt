package ie.dylangore.mad1.assignment1.models.storage

import ie.dylangore.mad1.assignment1.helpers.ValidationHelper
import ie.dylangore.mad1.assignment1.models.Location
import mu.KotlinLogging


/**
 * Class that manages storing location data in memory
 *
 */
class LocationMemoryStore: LocationStore {

    private val logger = KotlinLogging.logger {}

    private val locations: ArrayList<Location> = ArrayList()

    /**
     * Get a list of all stored locations
     *
     * @return a list of locations
     */
    override fun findAll(): List<Location> {
        return locations
    }

    /**
     * Get a single location by ID
     *
     * @param id the ID of the location to find
     * @return the Location object with the given ID
     */
    override fun findOne(id: Long): Location? {
        return locations.find { it.id == id}
    }

    /**
     * Add a new location
     *
     * @param location the new location to add
     */
    override fun add(location: Location) {
        location.id = getId()
        if (ValidationHelper.validateLocation(location)){
            locations.add(location)
            logger.info{ "New location added: $location"}
        }else{
            logger.error { "Location not added: invalid location data" }
        }
    }

    /**
     * Update an existing location
     *
     * @param location the location to update
     */
    override fun update(location: Location) {
        val foundLocation = findOne(location.id)
        if (foundLocation != null){
            logger.info("Updating location: $location")
            foundLocation.name = location.name
            foundLocation.latitude = location.latitude
            foundLocation.longitude = location.longitude
            foundLocation.altitude = location.altitude
        }else{
            logger.error("Location with ID ${location.id} not found, nothing to update")
        }
    }

    /**
     * Delete a location
     *
     * @param id the ID of the location to delete
     */
    override fun delete(id: Long) {
        val location = findOne(id)
        if (location != null){
            logger.info("Deleting location: $location")
            locations.remove(location)
        }else{
            logger.error("Location with ID $id not found, nothing to delete")
        }
    }

    /**
     * Delete all entries from the list
     */
    override fun empty() {
        locations.removeAll(locations)
    }

    /**
     * Get the next available element ID
     *
     * @return the new ID as a Long
     */
    private fun getId(): Long{
        return if (locations.isNotEmpty()) locations.last().id + 1 else 1
    }

}