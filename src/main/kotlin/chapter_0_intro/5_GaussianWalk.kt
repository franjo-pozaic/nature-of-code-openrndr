package chapter_0_intro

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.olive.oliveProgram

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
    }
    oliveProgram {
        val rt = renderTarget(width, height) { colorBuffer() }
        drawer.isolatedWithTarget(rt) {
            drawer.clear(ColorRGBa.WHITE)
        }
        val walker = WalkerGaussian(drawer, 0.0, 10.0)

        extend {
            walker.step()
            drawer.isolatedWithTarget(rt) {
                walker.draw()
            }
            drawer.image(rt.colorBuffer(0))
        }
    }
}