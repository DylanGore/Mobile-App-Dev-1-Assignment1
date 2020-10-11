package ie.dylangore.mad1.assignment1.datamodels

class Warning : ArrayList<Warning.WarningItem>(){
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
        val regions: List<String>,
        val severity: String,
        val status: String,
        val type: String,
        val updated: String
    )
}