package com.skycombat.game.model.gui.element

import com.skycombat.FakePointF
import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.bullet.Bullet
import com.skycombat.game.model.gui.element.bullet.ClassicBullet
import com.skycombat.game.model.gui.element.bullet.MultipleBullet
import com.skycombat.game.model.gui.element.bullet.collision.EnemyCollisionStrategy
import com.skycombat.game.model.gui.element.ghost.movement.LinearAimedPositionMovement
import org.junit.Assert.*
import org.junit.Test

class PlayerTest {

    // 40
    @Test
    fun `should remove if player is dead`(){
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(10f, 10f))
        player.health = 0f

        assertTrue(player.shouldRemove())
    }

    //41
    @Test
    fun `should not you be alive`(){
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(10f, 10f))
        player.health = 1f

        assertTrue(player.isAlive())
    }

//    @Test
//    fun `center is valid`(){
//        val width = 100f
//        val height = 100f
//        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
//
//        val tx : Float  = width/2F
//        val ty : Float = height/ 5 * 4


//        val f = FakePointF(tx,ty)
//        val x = f.x
//        val y = f.y
//        val center=FakePointF(player.getCenter().x,player.getCenter().y)
//
//        assertEquals(0.0f,center.dx)
//    }

    // 42
    @Test
    fun `collide with bullet`(){
        val width = 100f; val height = 100f
        //var enemy = JetEnemy(LaserBulletFactory(), Movement(1,2,3), DisplayDimension(width, height))
        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        val bullet = ClassicBullet(1f,1f, EnemyCollisionStrategy(),  Bullet.Direction.DOWN, DisplayDimension(width, height))

        player.setPosition(1f, 1f)

        assertTrue(player.collide(bullet))
    }

    @Test
    fun `collide with rectangle`(){
        val width = 100f; val height = 100f
        //var enemy = JetEnemy(LaserBulletFactory(), Movement(1,2,3), DisplayDimension(width, height))
        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        val bullet = MultipleBullet(0f,0f, EnemyCollisionStrategy(),  Bullet.Direction.DOWN, DisplayDimension(width, height))

        player.setPosition(10f, 20f)

        assertFalse(player.collide(bullet))
    }

    // 43
    @Test
    fun `check health if bullet collide`(){
        val width = 100f; val height = 100f
//        var enemy = JetEnemy(LaserBulletFactory(), Movement(1,2,3), DisplayDimension(width, height))
        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        val bullet = ClassicBullet(1f,1f, EnemyCollisionStrategy(),  Bullet.Direction.DOWN, DisplayDimension(width, height))

        player.setPosition(1f, 1f)
        bullet.applyCollisionEffects(player)

        assertTrue(player.getCurrentHealth()<player.getMaxHealth())
    }

    // 44
    @Test
    fun `check MAX_HEALTH with shield if bullet collide`(){
        val width = 100f; val height = 100f
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        val bullet = ClassicBullet(1f,1f, EnemyCollisionStrategy(),  Bullet.Direction.DOWN, DisplayDimension(width, height))

        player.applyShield(2)

        player.setPosition(1f, 1f)
        bullet.applyCollisionEffects(player)

        assertTrue(player.getCurrentHealth() == player.getMaxHealth())
    }

    // 45
    @Test
    fun `check shield is due to date`(){
        val width = 100f; val height = 100f
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        var bullet = ClassicBullet(1f,1f, EnemyCollisionStrategy(),  Bullet.Direction.DOWN, DisplayDimension(width, height))

        player.applyShield(1)
        player.update()

        assertFalse(player.hasShield())
    }

    //46
    @Test
    fun `get X`(){
        val width = 100f; val height = 100f
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        val hypotesX = width/ 2F

        assertEquals(player.getX(), hypotesX)
    }

    //47
    @Test
    fun `get Y`(){
        val width = 100f; val height = 100f
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        val hypotesY = height/ 5 * 4

        assertEquals(player.getY(), hypotesY)
    }
}

