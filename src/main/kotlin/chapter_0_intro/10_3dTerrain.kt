package chapter_0_intro

import org.openrndr.WindowMultisample
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.camera.Orbital
import org.openrndr.extra.meshgenerators.*
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector3

fun main() {
    application {
        configure {
            multisample = WindowMultisample.SampleCount(8)
        }
        oliveProgram {
            val plane = groundPlaneMesh(1.0, 1.0, 10, 10)

            val vertexBuffer = plane
            val vertexCount = vertexBuffer.vertexCount

            vertexBuffer.put {
                for (i in 0 until vertexCount) {
                    write(Vector3(Math.random(), Math.random(), 0.1))
                }
            }

//            vertexBuffer.put {
//                for (i in 0 until vertexCount) {
//                    write(Vector3(0.1, 0.1, 0.1))
//                }
//            }

            val texture = colorBuffer(256, 256)
            val s = texture.shadow
            for (y in 0 until 256) {
                for (x in 0 until 256) {
                    s[x, y] = ColorRGBa(x / 256.0, y / 256.0, 0.0, 1.0)
                }
            }

            s.upload()

            extend(Orbital()) {
                eye = Vector3(1.0, 1.0, 1.0)
            }
            extend {
                drawer.clear(ColorRGBa.PINK)
                drawer.shadeStyle = shadeStyle {
                    fragmentTransform = """
                        x_fill = texture(p_texture, va_texCoord0.xy);
                    """.trimIndent()
                    parameter("texture", texture)
                }
                drawer.drawStyle.cullTestPass = CullTestPass.FRONT
                drawer.vertexBuffer(plane, DrawPrimitive.TRIANGLES)
            }
        }
    }
}
