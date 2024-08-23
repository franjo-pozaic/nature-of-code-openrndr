package chapter_0_intro

import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.noise.Random
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
        val numberOfDots = 10
        val splatterDeviation = 100.0

        val rt = renderTarget(width, height) { colorBuffer() }
        drawer.isolatedWithTarget(rt) {
            drawer.clear(ColorRGBa.WHITE)
        }

        mouse.buttonDown.listen { mouseEvent: MouseEvent ->
            val x = mouseEvent.position.x
            val y = mouseEvent.position.y

            drawer.isolatedWithTarget(rt) {
                drawer.stroke = null
                drawer.fill = ColorRGBa.PINK.opacify(0.2)
                drawer.circle(x, y, 20.0)
                (1..numberOfDots).forEach {
                    val randomX = Random.gaussian(x, splatterDeviation)
                    val randomY = Random.gaussian(y, splatterDeviation)
                    val randomSize = Random.gaussian(10.0, 20.0)
                    drawer.circle(randomX, randomY, randomSize)
                }
            }
        }

        keyboard.keyDown.listen { keyEvent: KeyEvent ->
            if (keyEvent.key == KEY_SPACEBAR) {
                drawer.isolatedWithTarget(rt) {
                    drawer.clear(ColorRGBa.WHITE)
                }
            }
        }

        extend {

//            drawer.isolatedWithTarget(rt) {
//                (1..200).forEach {
//                    val randomX = Random.gaussian(width / 2.0, 60.0)
//                    drawer.fill = ColorRGBa.PINK.opacify(0.01)
//                    drawer.stroke = null
//                    drawer.circle(randomX, height / 2.0, 20.0)
//                }
//            }
            drawer.image(rt.colorBuffer(0))

        }
    }
}

