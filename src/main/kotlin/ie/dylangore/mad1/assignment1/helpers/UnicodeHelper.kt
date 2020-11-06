@file:Suppress("MemberVisibilityCanBePrivate")

package ie.dylangore.mad1.assignment1.helpers

/**
 * Functions to help with displaying unicode characters (Symbols & Emoji)
 */
object UnicodeHelper {

    // Symbols
    val symbolDegree = String(Character.toChars(0x2103))

    // Emoji
    val emojiClock = String(Character.toChars(0x1F550))
    val emojiTemp = String(Character.toChars(0x1F321))
    val emojiHumidity = String(Character.toChars(0x1F4A7))
    val emojiPressure = String(Character.toChars(0x2652))
    val emojiWind = String(Character.toChars(0x1F300))
    val emojiWeather = String(Character.toChars(0x26C5))
    val emojiCloud = String(Character.toChars(0x2601))
    val emojiSun = String(Character.toChars(0x1F324))
    val emojiMoon = String(Character.toChars(0x1F319))
    val emojiFair = String(Character.toChars(0x26C5))
    val emojiFog = String(Character.toChars(0x1F32B))
    val emojiRain = String(Character.toChars(0x1F327))

    /**
     * Convert the weather symbol property included in the weather forecast to an icon
     *
     * @param condition the weather condition
     * @return the icon
     */
    @Suppress("SpellCheckingInspection")
    fun getWeatherIcon(condition: String?):String{
        return when(condition){
            "clearsky", "clearsky_day" -> emojiSun
            "clearsky_night" -> emojiMoon
            "cloudy", "partlycloudy", "partlycloudy_night" -> emojiCloud
            "fair", "fair_day", "fair_night" -> emojiFair
            "fog" -> emojiFog
            "heavyrain", "lightrain" ,"heavyrainshowers"-> emojiRain
            else -> emojiSun
        }
    }
}