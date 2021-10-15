package com.openbank.marvel_app_heroes

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Utils {
    companion object {
        const val TAG = "Marvel"
        val timestamp = System.currentTimeMillis().toString()
        var hash = generateHash(timestamp, BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY)
        var isLoading = false

        fun generateHash(timestamp: String, publicKey: String, privateKey: String): String {
            return try {
                val value = timestamp + privateKey + publicKey
                val md5Encoder = MessageDigest.getInstance("MD5")
                val md5Bytes = md5Encoder.digest(value.toByteArray())
                BigInteger(1, md5Bytes).toString(16).padStart(32, '0')
            } catch (e: NoSuchAlgorithmException) {
                throw NoSuchAlgorithmException("cannot generate the api key", e)
            }
        }

       fun getPortraitIncredibleURL(path: String?, ext: String?): String {
           return if (path==null || ext == null) "" else "$path/portrait_incredible.$ext"
        }

        fun getStandardXLargeURL(path: String?, ext: String?): String {
            return if (path==null || ext == null) "" else "$path/standard_xlarge.$ext"
        }

        fun getLandscapeXLargeURL(path: String?, ext: String?): String {
            return if (path==null || ext == null) "" else "$path/landscape_xlarge.$ext"
        }
    }
}