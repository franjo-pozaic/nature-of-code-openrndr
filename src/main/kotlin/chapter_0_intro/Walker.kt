package chapter_0_intro

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer

class Walker(private val drawer: Drawer) {
    var x = drawer.bounds.dimensions.x / 2
    var y = drawer.bounds.dimensions.y / 2

    fun step() {
        val values = listOf(0,1,2,3)
        val choice = values.random()

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