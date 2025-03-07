package ie.dylangore.mad1.assignment1.models

/**
 * Data class for a list of weather warnings
 *
 */
class Warning : ArrayList<Warning.WarningItem>(){
    /**
     * Data class for an individual weather warning
     *
     * @property capId id provided by Met Éireann
     * @property certainty
     * @property description long description of the warning
     * @property expiry the expiry timestamp of the warning
     * @property headline short description of the warning
     * @property id warning id
     * @property issued the timestamp of when the warning was issued
     * @property level the level of the warning (Yellow, Orange, Red)
     * @property onset the timetamp of when the warning is in effect
     * @property regions a list of regions (counties and marine areas) included in this warning
     * @property severity the severity of the warning
     * @property status the warning status (Advisory, Warning, etc.)
     * @property type the warning type (Wind, Rain, etc.)
     * @property updated the timestamp of when the warning was last updated
     */
    data class WarningItem(
        val capId: String,
        val certainty: String,
        val description: String,
        val expiry: String,
        val headline: String,
        val id: Int,
        val issued: String,
        val level: String,
        val onset: String,
        var regions: List<String>,
        val severity: String,
        val status: String,
        val type: String,
        val updated: String
    )
}