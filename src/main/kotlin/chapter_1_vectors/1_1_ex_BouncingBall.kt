package chapter_1_vectors

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.olive.oliveProgram

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
        var x = width / 2.0
        var y = height / 2.0
        var xSpeed = 1.5
        var ySpeed = 1.0
        extend {
            drawer.clear(ColorRGBa.WHITE)
            drawer.fill = ColorRGBa.PINK
            drawer.stroke = null
            drawer.strokeWeight = 1.0
            drawer.circle(x, y, 10.0)
            if (x > width || x < 0) {
                xSpeed *= -1.0
            }
            if (y > height || y < 0) {
                ySpeed *= -1.0
            }
            x += xSpeed
            y += ySpeed
        }
    }
}