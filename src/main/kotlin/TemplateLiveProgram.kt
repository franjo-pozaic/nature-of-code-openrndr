import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.grayscale
import org.openrndr.draw.invert
import org.openrndr.draw.loadImage
import org.openrndr.draw.tint
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.shape.Rectangle
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

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
        val image = loadImage("data/images/cheeta.jpg")
        extend {
            drawer.clear(ColorRGBa.PINK)
            val areas = (0..10).flatMap { y ->
                (0..10).map { x ->
                    val source = Rectangle(x * (width / 10.0), y * (height / 10.0), width / 5.0, height / 5.0)
                    val target = Rectangle(x * (width / 10.0), y * (height / 10.0), width / 10.0, height / 10.0)
                    return@map source to target
                }
            }
            drawer.drawStyle.colorMatrix = tint(ColorRGBa.PINK) * grayscale()
            drawer.image(
                image, areas
            )
            drawer.fill = ColorRGBa.WHITE
            drawer.stroke = ColorRGBa.BLACK
            drawer.strokeWeight = 1.0
            drawer.circle(width / 6.0, height - abs(sin(seconds * 2)) * height, width / 8.0)


            // -- draw circle without fill, but with black stroke
            drawer.fill = ColorRGBa.PINK
            drawer.stroke = ColorRGBa.BLACK
            drawer.strokeWeight = 1.0
            drawer.circle(width / 6.0 + width / 3.0, height - abs(cos(seconds * 2)) * height, width / 8.0)

            // -- draw circle with white fill, but without stroke
            drawer.fill = ColorRGBa.WHITE
            drawer.stroke = null
            drawer.strokeWeight = 1.0
            drawer.circle(width / 6.0 + 2 * width / 3.0, height / 2.0, width / 8.0)
        }
    }
}