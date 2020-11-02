package ie.dylangore.mad1.assignment1.weather

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

/**
 * Test the observation stations parser
 */
class ObservationStationsTest {

    /**
     * Test that the HTTP request for observation stations returns data
     */
    @Test
    fun testStationsParserHTTP() {
        Assertions.assertNotNull(Stations.getMetEireannStations())
    }

    /**
     * Test the observation stations JSON parsing code
     */
    @Test
    fun testStationsParserLocalData() {
        Assertions.assertNotNull(Stations.getMetEireannStations(File("src/test/resources/met_stations.json").readText()))
    }
}