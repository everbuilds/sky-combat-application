package com.skycombat.game.model.factory.powerup

import com.skycombat.game.model.factory.bullet.ClassicBulletFactory
import com.skycombat.game.model.factory.bullet.GustBulletFactory
import com.skycombat.game.model.factory.bullet.LaserBulletFactory
import com.skycombat.game.model.factory.bullet.MultipleBulletFactory
import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.Player
import com.skycombat.game.model.gui.element.ghost.movement.LinearAimedPositionMovement
import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

class PowerUpFactoryTest{
    // 7
    @Test
    fun `life PowerUp Factory`(){
        val width = 100f
        val height = 100f
        val power = LifePowerUpFactory()
        val lifePower=power.generate(5f,2, Random(3),DisplayDimension(width, height))
        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        player.health = 1f
        lifePower.applyPowerUPEffects(player)
        assertEquals(player.health,player.getMaxHealth())
    }
    // 8
    @Test
    fun `guns PowerUp Factory`(){
        val width = 100f
        val height = 100f
        val power = GunsPowerUpFactory()
        val gunsPower=power.generate(5f,2, Random(11),DisplayDimension(width, height))
        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        player.health = 20f
        gunsPower.applyPowerUPEffects(player)
        when(player.weapon.bulletFactory){
            LaserBulletFactory::class -> assertEquals(player.weapon.bulletFactory, LaserBulletFactory::class)
            GustBulletFactory::class -> assertEquals(player.weapon.bulletFactory, GustBulletFactory::class)
            MultipleBulletFactory::class -> assertEquals(player.weapon.bulletFactory, MultipleBulletFactory::class)
            ClassicBulletFactory::class -> assertEquals(player.weapon.bulletFactory, ClassicBulletFactory::class)
        }
    }
    // 9
    @Test
    fun `shield PowerUp Factory`(){
        val width = 100f
        val height = 100f
        val power = ShieldPowerUpFactory()
        val shieldPower=power.generate(5f,2, Random(1),DisplayDimension(width, height))
        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        player.health = 20f
        shieldPower.applyPowerUPEffects(player)
        assertTrue(player.hasShield())
    }
    // 10
    @Test
    fun `seedGeneral PowerUp Factory`(){
        val width = 100f
        val height = 100f
        val power = SeedGeneralPowerUpFactory(2,DisplayDimension(width, height))
        val seedPower=power.generate()
        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        player.health = 20f
        seedPower.applyPowerUPEffects(player)
        assertTrue(seedPower.used)
    }
}