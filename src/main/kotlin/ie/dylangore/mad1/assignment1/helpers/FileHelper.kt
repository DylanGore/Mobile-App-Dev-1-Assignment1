package ie.dylangore.mad1.assignment1.helpers

import ie.dylangore.mad1.assignment1.logger
import java.io.*

object FileHelper {

    /**
     * Write data to a file
     *
     * @param fileName the file name to write to
     * @param data the data to write
     */
    fun write( fileName: String, data: String) {
        // Get the file
        val file = File(fileName)
        // Attempt to write the data
        try {
            val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: Exception) {
            logger.error { "Cannot read file: $e" }
        }
    }

    /**
     * Read data from a file
     *
     * @param fileName the file name to read from
     * @return the data read from the file
     */
    fun read(fileName: String): String {
        // Get the file
        val file = File(fileName)
        var str = ""
        // Attempt to read the file, line bu line
        try {
            val inputStreamReader = InputStreamReader(FileInputStream(file))
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                val line = bufferedReader.readLine()
                done = (line == null);
                if (line != null) partialStr.append(line);
            }
            inputStreamReader.close()
            str = partialStr.toString()
        } catch (e: FileNotFoundException) {
            logger.error { "Cannot Find file: $e" }
        } catch (e: IOException) {
            logger.error { "Cannot Read file: $e" }
        }
        return str
    }

    /**
     * Check if a file exists
     *
     * @param fileName the file name to look for
     * @return boolean reflecting file state
     */
    fun exists(fileName: String): Boolean {
        val file = File(fileName)
        return file.exists()
    }
}