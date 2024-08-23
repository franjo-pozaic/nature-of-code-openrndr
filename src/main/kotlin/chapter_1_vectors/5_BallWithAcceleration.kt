package chapter_1_vectors

import org.openrndr.KEY_ARROW_DOWN
import org.openrndr.KEY_ARROW_UP
import org.openrndr.KeyEvent
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector2

fun main() = application {
    configure {
        width = 640
        height = 480
        windowResizable = true
        windowAlwaysOnTop = true
    }
    oliveProgram {
        val mover = MoverKeyboardAccelerator(drawer)
        keyboard.keyDown.listen { keyEvent: KeyEvent ->
            when (keyEvent.key) {
                KEY_ARROW_UP -> {
                    mover.accelerate()
                }
                KEY_ARROW_DOWN -> {
                    mover.decelerate()
                }
            }
        }
        extend {
            mover.update()
            mover.show()
            mover.checkBounds()
        }
    }
}