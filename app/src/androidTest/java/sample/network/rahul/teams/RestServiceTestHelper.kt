package sample.network.rahul.teams

import android.content.Context

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @author rebeccafranks
 * @since 15/10/24.
 */
object RestServiceTestHelper {

    @Throws(Exception::class)
    fun convertStreamToString(input: InputStream): String {
        val reader = BufferedReader(InputStreamReader(input))
        val sb = StringBuilder()
        for(line in reader.readLine()) {
            sb.append(line)
        }
        reader.close()
        return sb.toString()
    }

    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String): String {
        val stream = context.resources.assets.open(filePath)

        val ret = convertStreamToString(stream)
        //Make sure you close all streams.
        stream.close()
        return ret
    }
}

