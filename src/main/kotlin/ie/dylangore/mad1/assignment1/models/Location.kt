package ie.dylangore.mad1.assignment1.models

/**
 * Data class to define the Location object
 *
 * @property id the location ID
 * @property name the name of the location
 * @property latitude the latitude of the location (decimal)
 * @property longitude the longitude of the location (decimal)
 * @property altitude the altitude of the location in meters
 */
data class Location (
    var id: Long = 0,
    var name: String = "",
    var latitude: Double = 0.0,
    var longitude:  Double = 0.0,
    var altitude: Int = 0
)