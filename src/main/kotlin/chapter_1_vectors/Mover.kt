package chapter_1_vectors

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2
import org.openrndr.math.Vector3

open class Mover(
    private val drawer: Drawer
) {
    private val radius = 10.0
    open var velocity = Vector2(1.0, 1.0)
    open var position = drawer.bounds.dimensions / 2.0

    open fun update() {
        position += velocity
    }

    fun limitVelocity(velocity: Vector2): Vector2 {
        val magnitude = velocity.length
        if (magnitude > 10.0) {
            return velocity.normalized * 10.0
        } else {
            return  velocity
        }
    }

    open fun show() {
        drawer.fill = ColorRGBa.PINK
        drawer.circle(position, radius)
    }

    fun checkBounds() {
        val width = drawer.bounds.width
        val height = drawer.bounds.height
        if (position.x !in 0.0 + radius..width - radius) {
            velocity *= Vector2(-1.0, 1.0)
        }
        if (position.y !in 0.0 + radius..height - radius) {
            velocity *= Vector2(1.0, -1.0)
        }
    }
}