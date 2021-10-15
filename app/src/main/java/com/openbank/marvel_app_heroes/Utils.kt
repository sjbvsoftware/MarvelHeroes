package com.openbank.marvel_app_heroes

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher.PRIVATE_KEY
import javax.crypto.Cipher.PUBLIC_KEY

class Constant {

    companion object {
        val TAG = "Marvel"
        val timestamp = System.currentTimeMillis().toString()

        var publicKey: String = BuildConfig.PUBLIC_KEY
        var privateKey: String = BuildConfig.PRIVATE_KEY
        var hash = generateHash(timestamp, publicKey, privateKey)

        const val OFFSET = 10
        const val LIMIT = 10
        //=https://gateway.marvel.com/v1/public/characters?ts=1633678964695&apikey=c15ba50968e1302c878271ac4228f763&hash=bced9a2d9037ec68590c578917780868&limit=100}
        /*  fun hash():  String {
              val input = "$ts$PRIVATE_KEY$API_KEY"
              val md = MessageDigest.getInstance("MD5")
              return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
          }*/

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
    }


}