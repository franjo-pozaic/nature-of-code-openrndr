package chapter_1_vectors

import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

class MoverPerlinAccelerator(private val drawer: Drawer): Mover(drawer) {
    var tx = 0.0
    var ty = 500.0
    var x = 0.0
    var y = 0.0
    val step = 0.01

    override fun update() {
        x = Random.simplex(tx, 0.0)
        y = Random.simplex(ty, 0.0)

        velocity = Vector2(x, y)
        tx += step
        ty += step
        position += velocity
    }
}