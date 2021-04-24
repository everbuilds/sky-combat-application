package com.skycombat.game.model.factory.bullet


import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.Player
import com.skycombat.game.model.gui.element.bullet.*
import com.skycombat.game.model.gui.element.bullet.collision.EnemyCollisionStrategy
import com.skycombat.game.model.gui.element.bullet.collision.PlayerCollisionStrategy
import com.skycombat.game.model.gui.element.enemy.JetEnemy
import com.skycombat.game.model.gui.element.enemy.movement.Movement
import com.skycombat.game.model.gui.element.ghost.movement.LinearAimedPositionMovement
import org.junit.Test

import org.junit.Assert.*


class BulletFactoryTest {
    // 1
    @Test
    fun generateClassicBulletFactory() {
        val width = 100f
        val height = 100f
        val power = ClassicBulletFactory()
        val classicBullet=power.generate(5f,2f, EnemyCollisionStrategy(), Bullet.Direction.UP, DisplayDimension(width, height))
        var check = false
//        val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
//        player.health=player.getMaxHealth()
//        classicBullet.applyCollisionEffects(player)

        if(classicBullet is ClassicBullet)
            check = true

//        assertEquals(player.getMaxHealth()-player.health, classicBullet.getDamage())
        assertTrue(check)
    }
    // 2
    @Test
    fun `generate Gust Bullet Factory` () {
        val width = 100f
        val height = 100f
        val power = GustBulletFactory()
        val gustBullet=power.generate(5f,2f, EnemyCollisionStrategy(), Bullet.Direction.UP, DisplayDimension(width, height))
        //val player = Player(0f, LinearAimedPositionMovement(), DisplayDimension(width, height))
        //player.health=player.getMaxHealth()
        //gustBullet.applyCollisionEffects(player)
        //assertEquals(player.getMaxHealth()-player.health, gustBullet.getDamage())
        var check = false
        if(gustBullet is GustBullet)
            check = true
        assertTrue(check)
    }
    // 3
    @Test
    fun `generate Laser Bullet Factory`() {
        val width = 100f
        val height = 100f
        val power = LaserBulletFactory()
        val laserBullet=power.generate(5f,2f, PlayerCollisionStrategy(), Bullet.Direction.DOWN, DisplayDimension(width, height))
//      val enemy = JetEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
//      laserBullet.applyCollisionEffects(enemy)
//      assertEquals(enemy.getMaxHealth()-enemy.health, laserBullet.getDamage())
        var check = false
        if(laserBullet is LaserBullet)
            check = true
        assertTrue(check)
    }
    // 4
    @Test
    fun `generate Multiple Bullet Factory`() {
        val width = 100f
        val height = 100f
        val power = MultipleBulletFactory()
        val multipleBullet=power.generate(5f,2f, PlayerCollisionStrategy(), Bullet.Direction.DOWN, DisplayDimension(width, height))
//      val enemy = JetEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
//      multipleBullet.applyCollisionEffects(enemy)
//      assertEquals(enemy.getMaxHealth()-enemy.health, multipleBullet.getDamage())
        var check = false
        if(multipleBullet is MultipleBullet)
            check = true
        assertTrue(check)
    }
    // 5
    @Test
    fun `Test Delay Between Generations`(){
        val bf1 = ClassicBulletFactory()
        val bf2 = GustBulletFactory()
        val bf3 = LaserBulletFactory()
        val bf4 = MultipleBulletFactory()

        assertTrue(bf1.delayBetweenGenerations()==50 && bf2.delayBetweenGenerations()==10 && bf3.delayBetweenGenerations()==100 && bf4.delayBetweenGenerations()==60 )
    }
}