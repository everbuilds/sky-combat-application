package com.skycombat.game.model.gui.element.ghost

import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.ghost.movement.LinearAimedPositionMovement
import com.skycombat.game.model.gui.element.bullet.Bullet
import com.skycombat.game.model.gui.element.bullet.ClassicBullet
import com.skycombat.game.model.gui.element.bullet.LaserBullet
import com.skycombat.game.model.gui.element.bullet.collision.EnemyCollisionStrategy
import org.junit.Test

import org.junit.Assert.*

class GhostTest {
    // 22
    @Test
    fun getCenter() {
        val width = 100f
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f, DisplayDimension(width, height))

        //val tx : Float  = width/2F
        //val ty : Float = height/ 5 * 4

        val center=ghost.getCenter().x
        assertEquals(0.0f,center)
    }

    // 23
    @Test
    fun getRadius() {
        val width = 100f
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        assertEquals(70f,ghost.getRadius())
    }

    // 24
    @Test
    fun `shouldRemove of ghost`() {
        val width = 100f
        val height = 100f

        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))

        ghost.kill()
        assertTrue(ghost.shouldRemove())
    }

    // 25
    @Test
    fun isAlive() {
        val width = 100f
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        assertTrue(ghost.isAlive())
    }

    // 26
    @Test
    fun update() {
        val width = 100f
        val height = 100f

        val vel = 2f

        val ghost = Ghost(LinearAimedPositionMovement(),vel,DisplayDimension(width, height))
        val ghost2 = Ghost(LinearAimedPositionMovement(),vel,DisplayDimension(width, height))
        val ghostElse = Ghost(LinearAimedPositionMovement(),vel,DisplayDimension(width, height))


        ghost.setX(100f)
        ghost.aimedPositionX = 101f
        ghost.update()

        ghost2.setX(102f)
        ghost2.aimedPositionX = 100f
        ghost2.update()

        ghostElse.setX(100f)
        ghostElse.aimedPositionX = 103f
        ghostElse.update()

        assertTrue(ghost.getX()==ghost.aimToPos() && ghost2.getX()==102f-vel && ghostElse.getX()==100f+vel)

    }

    // 27
    @Test
    fun setX() {
        val width = 100f
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        ghost.setX(2f)

        assertEquals(2f,ghost.getX())
    }


    // 28
    @Test
    fun `aim position of ghost`() {
        val width = 100f
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        ghost.aimedPositionX=5f
        assertEquals(5f,ghost.aimToPos())
    }

    // 29
    @Test
    fun velocity() {
        val width = 100f
        val height = 100f
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        assertEquals(2f,ghost.velocity())
    }

    // 30
    @Test
    fun `collideRectangle = not collide ghost with bullet`() {
        val width = 100f; val height = 100f
        val bullet = LaserBullet(1f,1f, EnemyCollisionStrategy(),  Bullet.Direction.DOWN, DisplayDimension(width, height))
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        ghost.getCenter()

        assertFalse(ghost.collide(bullet))

    }

    // 31
    @Test
    fun `CollideCircle = not collide ghost with bullet`() {
        val width = 100f; val height = 100f
        val bullet = ClassicBullet(1f,1f, EnemyCollisionStrategy(),  Bullet.Direction.DOWN, DisplayDimension(width, height))
        val ghost = Ghost(LinearAimedPositionMovement(),2f,DisplayDimension(width, height))
        ghost.getCenter()

        assertFalse(ghost.collide(bullet))
    }

}