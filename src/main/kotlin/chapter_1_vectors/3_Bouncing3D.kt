package chapter_1_vectors

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.camera.Orbital
import org.openrndr.extra.camera.OrbitalCamera
import org.openrndr.extra.camera.OrbitalControls
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.extra.meshgenerators.*
import org.openrndr.math.Matrix44
import org.openrndr.math.Vector2
import org.openrndr.math.Vector3
import org.openrndr.math.transforms.translate
import org.openrndr.shape.Rectangle
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

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
        windowAlwaysOnTop = true
    }

    oliveProgram {
        val ballRadius = 1.0
        val ball = sphereMesh(radius = ballRadius)
        val box = boxMesh(10.0, 10.0, 10.0)
        val texture = colorBuffer(256, 256)
        val s = texture.shadow
        for (y in 0 until 256) {
            for (x in 0 until 256) {
                s[x, y] = ColorRGBa(x/256.0, y/256.0, 0.0, 1.0)
            }
        }
        s.upload()


        var randomVector = Vector3(Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
        var velocity = randomVector / 10.0
        var position = Vector3(0.0, 0.0, 0.0)

        mouse.buttonDown.listen {
            randomVector = Vector3(Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
            velocity = randomVector / 5.0
        }

        // Function to reflect a velocity vector based on a normal vector
        fun reflect(velocity: Vector3, normal: Vector3): Vector3 {
            return velocity - normal * (2.0 * velocity.dot(normal))
        }

        extend(Orbital()) {
            eye = Vector3(10.0, 10.0, 10.0)
            lookAt = Vector3(0.0, 0.0, 0.0)
            userInteraction = true
        }
        extend {
            drawer.clear(ColorRGBa.PINK)

            drawer.strokeWeight = 100.0
            drawer.shadeStyle = shadeStyle {
                fragmentTransform = """
                        x_fill = texture(p_texture, va_texCoord0.xy);
                    """.trimIndent()
                parameter("texture", texture)
            }
            drawer.drawStyle.cullTestPass = CullTestPass.FRONT
            position += velocity
            val transform = Matrix44.translate(position)

            drawer.vertexBuffer(box, DrawPrimitive.LINES)

            drawer.model = transform
            drawer.vertexBuffer(ball, DrawPrimitive.TRIANGLES)

            val lowBound = -5.0 + ballRadius
            val highBound = 5.0 - ballRadius
            if (position.x !in lowBound..highBound) {
                velocity = reflect(velocity, Vector3.UNIT_X)
            }
            if (position.y !in lowBound..highBound) {
                velocity = reflect(velocity, Vector3.UNIT_Y)
            }
            if (position.z !in lowBound..highBound) {
                velocity = reflect(velocity, Vector3.UNIT_Z)
            }
        }
    }
}
