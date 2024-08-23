package chapter_0_intro

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.Random

class WalkerCustomProb(private val drawer: Drawer, private val mean: Double = 0.0, private val deviation: Double = 10.0) {
    var x = drawer.bounds.dimensions.x / 2
    var y = drawer.bounds.dimensions.y / 2
    var oldX = x
    var oldY = y

    fun acceptReject() {

    }

    fun step() {
        val xStep = Random.gaussian(mean, deviation)
        val yStep = Random.gaussian(mean, deviation)

        oldX = x
        oldY = y
        x += xStep
        y += yStep
    }

    fun draw() {
        drawer.stroke = ColorRGBa.PINK
        drawer.strokeWeight = 2.0
        drawer.lineSegment(oldX, oldY, x, y)
        drawer.rectangle(x, y, 1.0, 1.0)
    }
}