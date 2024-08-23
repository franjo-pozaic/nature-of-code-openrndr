package chapter_2_forces

import org.openrndr.KEY_ARROW_DOWN
import org.openrndr.KEY_ARROW_UP
import org.openrndr.KeyEvent
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector2

fun main() = application {
    configure {
        width = 320
        height = 240
        windowResizable = true
        windowAlwaysOnTop = true
    }
    oliveProgram {
        val upwardForce = Vector2(0.0, -0.5)
        val mover = MoverBalloon(drawer)

        extend {
            mover.applyForce(upwardForce)
            mover.checkBounds()
            mover.update()
            mover.show()
        }
    }
}