package ie.dylangore.mad1.assignment1.models.storage

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ie.dylangore.mad1.assignment1.helpers.FileHelper.exists
import ie.dylangore.mad1.assignment1.helpers.FileHelper.read
import ie.dylangore.mad1.assignment1.helpers.FileHelper.write
import ie.dylangore.mad1.assignment1.helpers.ValidationHelper
import ie.dylangore.mad1.assignment1.models.Location
import mu.KotlinLogging
import java.lang.reflect.Type


/**
 * Class that manages storing location data in memory
 *
 */
class LocationJSONStore: LocationStore {

    private val jsonFile = "locations.json"
    private val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
    private val listType: Type = object : TypeToken<java.util.ArrayList<Location>>() {}.type

    private val logger = KotlinLogging.logger {}

    private var locations: ArrayList<Location> = ArrayList()

    /**
     * Runs when the class is initialised
     */
    init {
        // If a JSON file exists, import the data from it
        if (exists(jsonFile)) {
            deserialize()
        }
    }

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
            serialize()
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
            // Save the updated list to the JSON file
            serialize()
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
            // Save the updated list to the JSON file
            serialize()
        }else{
            logger.error("Location with ID $id not found, nothing to delete")
        }
    }

    /**
     * Delete all entries from the list
     */
    override fun empty() {
        locations.removeAll(locations)
        serialize()
    }

    /**
     * Get the next available element ID
     *
     * @return the new ID as a Long
     */
    private fun getId(): Long{
        return if (locations.isNotEmpty()) locations.last().id + 1 else 1
    }

    /**
     * Write the current version of the locations list to a JSON file
     */
    private fun serialize() {
        // Convert the existing list to a JSON String
        val jsonString = gsonBuilder.toJson(locations, listType)
        // Write the JSON data to the file
        write(jsonFile, jsonString)
    }

    /**
     * Read the JSON file and replace the current contents of the locations list with
     * the data from the file
     */
    private fun deserialize() {
        // Get the JSON data as a String
        val jsonString = read(jsonFile)
        // Convert the JSON string to a list and replace the existing list
        locations = Gson().fromJson(jsonString, listType)
    }

}