package chapter_1_vectors

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
        windowResizable = true
    }
    oliveProgram {
        val rt = renderTarget(width, height) { colorBuffer() }

        drawer.isolatedWithTarget(rt) {
            drawer.clear(ColorRGBa.WHITE)
        }
        val walker = MoverPerlinAccelerator(drawer)

        extend {
            walker.update()
            drawer.isolatedWithTarget(rt) {
                drawer.clear(ColorRGBa.WHITE)
                walker.checkBounds()
                walker.show()
            }
            drawer.image(rt.colorBuffer(0))
        }
    }
}