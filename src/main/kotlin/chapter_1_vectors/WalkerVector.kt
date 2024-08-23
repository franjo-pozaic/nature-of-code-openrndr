package chapter_1_vectors

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2

class WalkerVector(private val drawer: Drawer) {
    var x = drawer.bounds.dimensions.x / 2
    var y = drawer.bounds.dimensions.y / 2
    var position = Vector2(x, y)

    fun step(bias: Pair<String, String> = "" to "") {
        val list = mutableListOf(0, 1, 2, 3)
        val (leftOrRight, upOrDown) = bias

        when (leftOrRight) {
            "right" -> list.add(0)
            "left" -> list.add(1)
        }
        when (upOrDown) {
            "up" -> list.add(2)
            "down" -> list.add(3)
        }
        val choice = list.random()

        when (choice) {
            0 -> this.position += Vector2(1.0, 0.0)
            1 -> this.position += Vector2(-1.0, 0.0)
            2 -> this.position += Vector2(0.0, 1.0)
            3 -> this.position += Vector2(0.0, -1.0)
        }
    }

    fun draw() {
        drawer.stroke = ColorRGBa.PINK
        drawer.rectangle(position, 1.0, 1.0)
    }
}