package chapter_0_intro

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.Random

class WalkerPerlin(private val drawer: Drawer) {
    var xMax = drawer.bounds.dimensions.x
    var yMax = drawer.bounds.dimensions.y
    var tx = 0.0
    var ty = 500.0
    var x = 0.0
    var y = 0.0
    val step = 0.01
    val mapX = rangeMap(-1.0, 1.0, 0.0, xMax)
    val mapY = rangeMap(-1.0, 1.0, 0.0, yMax)

    fun step() {
        x = Random.simplex(tx, 0.0)
        y = Random.simplex(ty, 0.0)

        tx += step
        ty += step
    }

    fun draw() {
        drawer.stroke = ColorRGBa.PINK
        drawer.strokeWeight = 2.0
        val x1 = mapX(x)
        val y1 = mapY(y)
        drawer.circle(x1, y1, 10.0)
    }
}