package ie.dylangore.mad1.assignment1.weather

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class WarningsTest {

    @Test
    fun testWarningsParserHTTP() {
        Assertions.assertNotNull(Warnings.getWeatherWarnings())
    }

    @Test
    fun testWarningsParserLocalData() {
        Assertions.assertNotNull(Warnings.getWeatherWarnings(File("src/test/resources/one_warning.json").readText()))
        Assertions.assertNotNull(Warnings.getWeatherWarnings(File("src/test/resources/two_warnings.json").readText()))
        Assertions.assertNotNull(Warnings.getWeatherWarnings(File("src/test/resources/empty.json").readText()))
    }
}