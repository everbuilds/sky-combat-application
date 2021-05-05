package com.skycombat.game.model.gui.element.powerup

import com.skycombat.game.model.factory.bullet.LaserBulletFactory
import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.Player
import com.skycombat.game.model.gui.element.bullet.Bullet
import com.skycombat.game.model.gui.element.bullet.collision.PlayerCollisionStrategy
import com.skycombat.game.model.gui.element.enemy.Enemy
import com.skycombat.game.model.gui.element.ghost.movement.LinearAimedPositionMovement
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class PowerUpTest {
    // 37
    @Test
    fun `shouldApply LifePowerUp`() {
        val width = 100f;
        val height = 100f
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(10f, 10f))
        player.health = 0f
        val power = LifePowerUp(1f, 1f, 2f, DisplayDimension(width, height))

        player.setPosition(1f, 1f)
        power.applyPowerUPEffects(player)

        assertTrue(power.shouldApply(player))
    }
    // 38
    @Test
    fun `shouldApply PowerUp`() {
        val width = 100f
        val height = 100f
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(10f, 10f))
//        val power = GunsPowerUp(1f, 1f, LaserBulletFactory(), DisplayDimension(width, height))
        val power: PowerUp = Mockito.mock(
            PowerUp::class.java,
            Mockito.CALLS_REAL_METHODS
        )

        assertTrue(power.shouldApply(player))
    }
    // 39
    @Test
    fun update() {
        val width = 2f
        val height = 10f
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(10f, 10f))
//        val power = GunsPowerUp(1f, 1f, LaserBulletFactory(), DisplayDimension(width, height))
        val power: PowerUp = Mockito.mock(
            PowerUp::class.java,
            Mockito.CALLS_REAL_METHODS
        )

        power.update()
        assertTrue(power.collide(player))
    }

    // 40
    @Test
    fun shouldRemove() {
        val width = 100f;
        val height = 100f
        val player = Player(10f, LinearAimedPositionMovement(), DisplayDimension(10f, 10f))
        val power = ShieldPowerUp(1f, 1f, 2, DisplayDimension(width, height))

        power.applyPowerUPEffects(player)

        assertTrue(power.shouldRemove())
    }

}