package com.dreamsunited.falconbrick.utils

import android.content.res.Resources
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.InputStream
import java.io.Reader
import java.io.StringWriter
import java.io.Writer

interface AssetManagerHelper {
    fun <T> constructUsingGson(id: Int, type: Class<T>?): T
}

class AssetManagerHelperImpl(private val resources: Resources) : AssetManagerHelper {

    override fun <T> constructUsingGson(id: Int, type: Class<T>?): T {
        return GsonBuilder().create().fromJson(jsonFileReader(id), type)
    }

    private fun jsonFileReader(id: Int): String {
        val inputStream: InputStream = resources.openRawResource(id)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { `is` ->
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }
        return writer.toString()
    }
}