package ie.dylangore.mad1.assignment1.weather

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class ForecastTest {
    /**
     * Test that the HTTP request to the Met.No API returns data
     *
     */
    @Test
    fun testForecastParserHTTP() {
        Assertions.assertNotNull(Forecasts.getForecast())
    }

    /**
     * Test the weather forecast JSON parsing code
     *
     */
    @Test
    fun testForecastParserLocalData() {
        Assertions.assertNotNull(Forecasts.getForecast(File("src/test/resources/sample_forecast.json").readText()))
    }
}