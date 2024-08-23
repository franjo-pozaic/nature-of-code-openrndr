package chapter_0_intro

import org.openrndr.MouseEvents
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

fun get_bias(walker: WalkerBiased, mouse: MouseEvents): Pair<String, String> {
    var left_right = "left"
    var up_down = "down"
    if (walker.x < mouse.position.x)
        left_right = "right"
    if (walker.y < mouse.position.y)
        up_down = "up"

    return left_right to up_down
}

fun main() = application {
    configure {
        width = 640
        height = 480
    }
    oliveProgram {
        val rt = renderTarget(width, height) { colorBuffer() }
        drawer.isolatedWithTarget(rt) {
            drawer.clear(ColorRGBa.BLACK)
        }
        val walker = WalkerBiased(drawer)

        extend {
            val bias = get_bias(walker, mouse)
            walker.step(bias)
            drawer.isolatedWithTarget(rt) {
                walker.draw()
            }
            drawer.image(rt.colorBuffer(0))
        }
    }
}