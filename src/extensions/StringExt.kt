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
