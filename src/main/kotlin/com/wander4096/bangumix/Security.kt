package com.wander4096.bangumix

import java.security.MessageDigest

object Security {
    fun encodeMD5(password: String) {
        val encodeMethod = MessageDigest.getInstance("MD5")
        encodeMethod.update(password.toByteArray())
        val bytesDigest = encodeMethod.digest()

    }

}