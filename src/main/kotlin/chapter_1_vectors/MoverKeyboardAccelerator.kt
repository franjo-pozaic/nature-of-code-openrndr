package chapter_1_vectors

import org.openrndr.draw.Drawer

class MoverKeyboardAccelerator(private val drawer: Drawer): Mover(drawer) {
    fun accelerate() {
        val length = velocity.length
        val velocityNormalized = velocity.normalized
        velocity = velocityNormalized * length * 1.2
    }

    fun decelerate() {
        val length = velocity.length
        val velocityNormalized = velocity.normalized
        velocity = velocityNormalized * length * 0.8
    }
}