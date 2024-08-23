package chapter_0_intro

fun rangeMap(min: Double, max: Double, minTo: Double, maxTo: Double): (Double) -> Double {
    val sourceRange = max - min
    val destinationRange = maxTo - minTo
    return { value: Double ->
        val distanceFromMin = value - min
        val ratio = distanceFromMin / sourceRange
        val add = ratio * destinationRange
        minTo + add
    }
}