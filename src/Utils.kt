import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun String.readFileAsLines(): List<String> =
    File("src", "$this.txt").readLines()

/**
 * Reads whole content from the given input txt file.
 */
fun String.readFileAsText(): String =
    File("src", "$this.txt").readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/** Converts boolean to 1 if true, 0 if false */
fun Boolean.toInt() = if (this) 1 else 0

/** Verifies if one range contains the other */
operator fun IntRange.contains(other: IntRange): Boolean =
    contains(other.first) && contains(other.last)