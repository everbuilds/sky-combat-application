package com.skycombat.game.model.geometry

import com.skycombat.FakeCircle
import com.skycombat.FakeRectangle
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ShapeTest {
    @Test
    fun `Circle colliding Rectangle`() {
        var circle : FakeCircle = Mockito.mock(
            FakeCircle::class.java,
            Mockito.CALLS_REAL_METHODS
        )
        var rectangle : FakeRectangle = Mockito.mock(
            FakeRectangle::class.java,
            Mockito.CALLS_REAL_METHODS
        )

        circle.x = 1f
        circle.y = 1f

        rectangle.x = 1f
        rectangle.y = 1f

        val cc= circle.getCenter()
        val rc= rectangle.getPosition()

        println(cc.x)
        println(cc.y)
        println(rc.top)
        println(rc.left)
//        + " " + cc.y + " " + rc.top + " " + rc.left )

        assertTrue(circle.collide(rectangle))
    }
}