package chapter_1_vectors

import org.openrndr.MouseEvents
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

class MoverMouseAccelerator(private val drawer: Drawer): Mover(drawer) {
    var x = 0.0
    var y = 0.0
    val step = 0.01

    fun update(mousePosition: Vector2) {
        if (mousePosition != Vector2(0.0, 0.0)) {
            var acceleration = Vector2(mousePosition.x - position.x, mousePosition.y - position.y)
            acceleration *= 0.002
            velocity += acceleration
        }

        velocity = limitVelocity(velocity)

        position += velocity
    }
}