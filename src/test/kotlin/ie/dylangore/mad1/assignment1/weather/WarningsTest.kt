package ie.dylangore.mad1.assignment1.weather

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

/**
 * Test the weather warnings parser
 *
 */
class WarningsTest {

    /**
     * Test that the HTTP request for weather warnings returns data
     *
     */
    @Test
    fun testWarningsParserHTTP() {
        Assertions.assertNotNull(Warnings.getWeatherWarnings())
    }

    /**
     * Test the weather warnings JSON parsing code to make sure it can handle
     * various types of warnings (including an empty array)
     *
     */
    @Test
    fun testWarningsParserLocalData() {
        Assertions.assertNotNull(Warnings.getWeatherWarnings(File("src/test/resources/one_warning.json").readText()))
        Assertions.assertNotNull(Warnings.getWeatherWarnings(File("src/test/resources/two_warnings.json").readText()))
        Assertions.assertNotNull(Warnings.getWeatherWarnings(File("src/test/resources/empty.json").readText()))
    }
}