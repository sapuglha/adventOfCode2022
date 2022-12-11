package extensions

/** Verifies if one range contains the other */
operator fun IntRange.contains(other: IntRange): Boolean =
    contains(other.first) && contains(other.last)