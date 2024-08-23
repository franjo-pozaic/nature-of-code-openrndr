package chapter_0_intro

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer

class WalkerBiased(private val drawer: Drawer) {
    var x = drawer.bounds.dimensions.x / 2
    var y = drawer.bounds.dimensions.y / 2

    fun step(bias: Pair<String, String> = "" to "") {
        val list = mutableListOf(0, 1, 2, 3)
        val (leftOrRight, upOrDown) = bias

        when (leftOrRight) {
            "right" -> list.add(0, 0)
            "left" -> list.add(0, 1)
        }
        when (upOrDown) {
            "up" -> list.add(0, 2)
            "down" -> list.add(0, 3)
        }
        val choice = list.random()

        when (choice) {
            0 -> this.x++
            1 -> this.x--
            2 -> this.y++
            3 -> this.y--
        }
    }

    fun draw() {
        drawer.stroke = ColorRGBa.PINK
        drawer.rectangle(x, y, 1.0, 1.0)
    }
}