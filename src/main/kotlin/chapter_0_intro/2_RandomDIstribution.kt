package chapter_0_intro

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.Random
import org.openrndr.extra.olive.oliveProgram
import kotlin.math.floor

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */

fun main() = application {
    configure {
        width = 640
        height = 480
        windowResizable = true
    }
    oliveProgram {
        val total = 40
        val distribution = IntArray(total)

        extend {
            val random = Random.int(0, total)
            distribution[random]++

            val rectangleWidth = floor((width / total).toDouble())
            val startY = height.toDouble()

            distribution.forEachIndexed { index, numberOfOccurrences ->
                val x = index * rectangleWidth
                val rectangleHeight = numberOfOccurrences.toDouble()
                drawer.fill = ColorRGBa.PINK
                drawer.stroke = ColorRGBa.BLUE
                drawer.rectangle(x, startY - rectangleHeight, rectangleWidth, rectangleHeight)
            }
        }
    }
}