package utils

import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class ReadJsonUtils {
    fun <T> getJsonToMock(fileName: String, className: Class<T>): T {
        val json = getJsonFromResources(fileName)
        val gson = GsonBuilder().create()
        return gson.fromJson(json, className)
    }

    fun getJsonFromResources(fileName: String): String {
        var reader: BufferedReader? = null
        var lineResult = ""
        try {
            reader = BufferedReader(
                    InputStreamReader(this.javaClass.classLoader.getResourceAsStream(fileName), "UTF-8"))

            // do reading, usually loop until end of file reading
            var line: String?
            do {
                line = reader.readLine()
                if (line != null) {
                    lineResult += line
                }
            } while (line != null)
        } catch (e: IOException) {
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                }

            }
        }
        return lineResult
    }

}
