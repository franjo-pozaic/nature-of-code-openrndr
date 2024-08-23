package chapter_1_vectors

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector2

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */

fun main() = application {
    configure {
        width = 320
        height = 240
        windowResizable = true
        windowAlwaysOnTop = true
    }
    oliveProgram {
        var position = Vector2(width / 2.0, height / 2.0)
        var speed = Vector2(2.0, 1.0)
        extend {
            drawer.clear(ColorRGBa.WHITE)
            drawer.fill = ColorRGBa.PINK
            drawer.stroke = null
            drawer.strokeWeight = 1.0
            drawer.circle(position, 10.0)
            if (position.x !in 0.0..width.toDouble()) {
                speed *= Vector2(-1.0, 1.0)
            }
            if (position.y !in 0.0..height.toDouble()) {
                speed *= Vector2(1.0, -1.0)
            }
            position += speed
        }
    }
}