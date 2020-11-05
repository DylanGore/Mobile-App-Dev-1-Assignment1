package ie.dylangore.mad1.assignment1.datamodels

data class Location (
    var id: Long = 0,
    var name: String,
    var latitude: Double,
    var longitude:  Double,
    var altitude: Int = 0
)