package chapter_0_intro

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.noise.Random
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.map

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
        windowAlwaysOnTop = true
    }
    oliveProgram {
        val cb = colorBuffer(width, height)
        val xStep = 0.005
        val yStep = 0.005


        extend {
            cb.shadow.download()
            var xNoise = 0.0
            var yNoise = 0.0

            for (x in 0 until cb.width) {
                yNoise = 0.0
                for (y in 0 until cb.height) {
                    val xy = Random.simplex(xNoise, yNoise, seconds * 2)
                    val mapped = map(-1.0.. 1.0, 0.0..1.0, xy)
                    cb.shadow[x, y] = ColorRGBa(mapped, mapped, mapped)
                    yNoise += yStep
                }
                xNoise += xStep
            }
            cb.shadow.upload()
            drawer.image(cb)
        }
    }
}