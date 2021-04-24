package com.skycombat.game.model.gui.element.enemy

import com.skycombat.game.model.factory.bullet.GustBulletFactory
import com.skycombat.game.model.factory.bullet.LaserBulletFactory
import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.bullet.Bullet
import com.skycombat.game.model.gui.element.bullet.LaserBullet
import com.skycombat.game.model.gui.element.bullet.collision.PlayerCollisionStrategy
import com.skycombat.game.model.gui.element.enemy.movement.Movement

import org.junit.Test

import org.junit.Assert.*

class EnemyTest {
    // 17
    @Test
    fun `getHeight of enemy`() {
        val width = 100f
        val height = 100f
        val enemy = JetEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val high = 180f
        assertNotEquals(high,enemy.getHeight())
    }
    // 18
    @Test
    fun `getMaxHealth plane enemy`() {
        val width = 100f
        val height = 100f
        val coeff = 6f
        val enemy = PlaneEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height), coeff)
        val health = 200f * coeff
        assertEquals(health , enemy.getMaxHealth())
    }
    // 19
    @Test
    fun `getMaxHealth jet enemy`() {
        val width = 100f
        val height = 100f
        val enemy = JetEnemy(LaserBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val health = 300f
        assertEquals(health , enemy.getMaxHealth())
    }
    // 20
    @Test
    fun `shouldRemove of enemy by health`() {
        val width = 100f
        val height = 100f
        val enemy = SpaceShipEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        enemy.health = 0f

        assertTrue(enemy.shouldRemove())
    }

    //36
    @Test
    fun `shouldRemove of enemy over the screen`() {
        val width = 100f
        val height = 100f
        val enemy = PlaneEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        enemy.left = -160f

        assertTrue(enemy.shouldRemove())
    }


//    @Test
//    fun `getPosition spaceShip`(){
//        val width = 100f
//        val height = 100f
//        val enemy = SpaceShipEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
//        assertNotSame(1f,enemy.getPosition())
//    }
//
//    @Test
//    fun `getPosition plane`(){
//        val width = 100f
//        val height = 100f
//        val enemy = PlaneEnemy(ClassicBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
//        println(enemy.getPosition().left)
//        assertNotEquals(0.0f,enemy.getPosition().left)
//    }

    //21
    @Test
    fun `Testing Rectangle Colliding Bullet`(){
        val width = 100f
        val height = 100f
        val enemy = SpaceShipEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val bullet = LaserBullet(enemy.left, enemy.top, PlayerCollisionStrategy(), Bullet.Direction.UP, DisplayDimension(width, height))
        enemy.health=1f

        bullet.applyCollisionEffects(enemy)

        assertTrue(bullet.shouldRemove() && enemy.shouldRemove())
    }
}