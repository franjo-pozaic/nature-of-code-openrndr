package chapter_0_intro

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.noise.Random
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Polar
import org.openrndr.math.Vector2
import org.openrndr.math.map

/**
 *  This is a template for a live program.
 *
 *  It uses oliveProgram {} instead of program {}. All code inside the
 *  oliveProgram {} can be changed while the program is running.
 */

fun main() = application {
    configure {
        width = 640
        height = 480
        windowResizable = true
    }

    oliveProgram {
        var tree = Branch.createTree(4, 4)

        mouse.buttonDown.listen {
            tree = Branch.createTree(4, 6)
        }

        extend {
            drawer.clear(ColorRGBa.WHITE)
            drawer.stroke = ColorRGBa.PINK
            drawBranch(drawer, tree, Vector2(width / 2.0, height - 50.0),270.0, seconds)
        }
    }
}

fun changeAngle(branch: Branch) {

}

fun drawBranch(drawer: Drawer, branch: Branch, position: Vector2, parentAngle: Double, seconds: Double) {
    val endPosition = position + Vector2.fromPolar(Polar(branch.angle + parentAngle, branch.length))
    drawer.strokeWeight = branch.thickness
    drawer.lineSegment(position, endPosition)
    for (child in branch.children) {
        val offset = map(-1.0, 1.0, -40.0, 40.0, Random.simplex(seconds / 5, branch.randomValue))
        drawBranch(drawer, child, endPosition, branch.angle + parentAngle + offset, seconds)
    }
}

data class Branch(
    var length: Double,
    var angle: Double,
    var thickness: Double,
    var children: MutableList<Branch>,
    val randomValue: Double = Random.rnd.nextDouble()
) {
    fun addChild(child: Branch) {
        children.add(child)
    }
    fun rotate(offset: Double) {
        angle += offset
    }

    companion object {
        fun createTree(depth: Int, maxChildren: Int): Branch {
            val root = Branch(length = 150.0, angle = 0.0, thickness = 5.0, children = mutableListOf())
            if (depth > 0) {
                populateBranch(root, depth, maxChildren)
            }
            return root
        }

        private fun populateBranch(parent: Branch, depth: Int, maxChildren: Int) {
            if (depth == 0) return
            val numChildren = (1..maxChildren).random()
            repeat(numChildren) {
                val child = Branch(
                    length = parent.length * 0.7,
                    angle = (-50..50).random().toDouble(),
                    thickness = parent.thickness * (0.6),
                    children = mutableListOf()
                )
                parent.addChild(child)
                populateBranch(child, depth - 1, maxChildren)
            }
        }
    }
}
