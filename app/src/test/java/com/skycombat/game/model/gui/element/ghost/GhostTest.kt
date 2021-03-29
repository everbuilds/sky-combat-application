package com.skycombat.game.model.gui.element.ghost

import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.Player
import com.skycombat.game.model.gui.element.ghost.movement.LinearAimedPositionMovement
import com.skycombat.game.model.gui.element.powerup.ShieldPowerUp
import android.graphics.Paint
import android.graphics.PointF
import org.junit.Test

import org.junit.Assert.*

class GhostTest {

    @Test
    fun getCenter() {
        val width = 100f
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f, DisplayDimension(width, height))

        val tx : Float  = width/2F
        val ty : Float = height/ 5 * 4


        val f = PointF(tx,ty)
        val x = f.x
        val y = f.y
        val center=ghost.getCenter().x
        assertEquals(0.0f,center)
    }

    @Test
    fun getRadius() {
        val width = 100f;
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        assertEquals(70f,ghost.getRadius())
    }

    @Test
    fun shouldRemove() {
        val width = 100f;
        val height = 100f

        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))

        ghost.kill()
        assertTrue(ghost.shouldRemove())
    }

    @Test
    fun isAlive() {
        val width = 100f;
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        assertTrue(ghost.isAlive())
    }

    @Test
    fun draw() {
    }

    @Test
    fun update() {
    }

    @Test
    fun setX() {
        val width = 100f;
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        val x=ghost.setX(2f)

        assertEquals(2f,ghost.getX())
    }


    @Test
    fun aimToPos() {
    }

    @Test
    fun velocity() {
        val width = 100f;
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        assertEquals(2f,ghost.velocity())
    }

    @Test
    fun collide() {
    }

    @Test
    fun testCollide() {
    }

    @Test
    fun testCollide1() {
    }
}