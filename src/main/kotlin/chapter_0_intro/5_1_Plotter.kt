package chapter_0_intro

import kotlinx.coroutines.delay
import org.openrndr.PresentationMode
import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.noise.Random
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.launch
import org.openrndr.math.mod

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */
fun Program.frameRate(frameRate: Long) {
    window.presentationMode = PresentationMode.MANUAL

    launch {
        val t = 1000.0 / frameRate // in millis
        val t0 = mod(seconds, t / 1000.0) // in seconds
        val d = (t - t0 * 1000).toLong() // in millis

        if (d > 0L) delay(d)

        window.requestDraw()
    }
}

fun main() = application {
    configure {
        width = 640
        height = 480
        windowResizable = true
        windowAlwaysOnTop = true
    }
    oliveProgram {
        val rt = renderTarget(width, height) { colorBuffer() }
        val mapX = rangeMap(-1.0, 1.0, 0.0, height.toDouble())
        var t = 0.0
        val step = 0.001

        // frameRate(30L)

        drawer.isolatedWithTarget(rt) {
            drawer.clear(ColorRGBa.WHITE)
        }

        extend {
            drawer.isolatedWithTarget(rt) {
                drawer.clear(ColorRGBa.WHITE)
                (0..width).forEachIndexed() { index, _ ->
                    if (index % 5 == 0) {
                        t = seconds + step * index
                        val y = Random.simplex(t, 0.0)
                        val mapped = mapX(y)
                        drawer.stroke = ColorRGBa.PINK
                        drawer.circle(index.toDouble(), mapped, 30.0)
                    }
                }
            }
            drawer.image(rt.colorBuffer(0))
        }
    }
}