package extensions

/** Converts boolean to 1 if true, 0 if false */
fun Boolean.toInt() = if (this) 1 else 0