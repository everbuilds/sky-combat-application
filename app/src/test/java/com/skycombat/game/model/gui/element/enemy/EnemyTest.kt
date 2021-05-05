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
import org.mockito.Mockito

class EnemyTest {
    // 19
    @Test
    fun `getHeight of jet enemy`() {
        val width = 100f
        val height = 100f
        val enemy = JetEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val high = 180f
        assertNotEquals(high,enemy.getHeight())
    }

    //20
    @Test
    fun `getHeight of plane enemy`() {
        val width = 100f
        val height = 100f
        val enemy = PlaneEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val high = 150f
        assertEquals(high,enemy.getHeight())
    }

    //21
    @Test
    fun `getHeight of space ship enemy`() {
        val width = 100f
        val height = 100f
        val enemy = SpaceShipEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val high = 250f
        assertEquals(high,enemy.getHeight())
    }

    // 22
    @Test
    fun `getMaxHealth plane enemy`() {
        val width = 100f
        val height = 100f
        val coeff = 1f
        val enemy = PlaneEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height), coeff)
        val health = 200f * coeff
        assertEquals(health , enemy.getMaxHealth())
    }
    // 23
    @Test
    fun `getMaxHealth jet enemy`() {
        val width = 100f
        val height = 100f
        val enemy = JetEnemy(LaserBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val health = 300f
        assertEquals(health , enemy.getMaxHealth())
    }
    //24
    @Test
    fun `shouldRemove of enemy`() {
        //val width = 100f
        //val height = 100f
        //val enemy = SpaceShipEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val enemy: Enemy = Mockito.mock(
            Enemy::class.java,
            Mockito.CALLS_REAL_METHODS
        )
        enemy.health = 0f

        assertTrue(enemy.shouldRemove())
    }

    //25
    @Test
    fun `shouldRemove of enemy outside the screen bounds`() {
//        val width = 100f
//        val height = 100f
//        val enemy = PlaneEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val enemy: Enemy = Mockito.mock(
            Enemy::class.java,
            Mockito.CALLS_REAL_METHODS
        )
        enemy.left = -160f

        assertTrue(enemy.shouldRemove())
    }

    //26
    @Test
    fun `Testing Rectangle Colliding Bullet`(){
        val width = 100f
        val height = 100f
//        val enemy = SpaceShipEnemy(GustBulletFactory(), Movement(1,2,3),  DisplayDimension(width, height))
        val enemy: Enemy = Mockito.mock(
            Enemy::class.java,
            Mockito.CALLS_REAL_METHODS
        )
        val bullet = LaserBullet(enemy.left, enemy.top, PlayerCollisionStrategy(), Bullet.Direction.UP, DisplayDimension(width, height))
        enemy.health=1f

        bullet.applyCollisionEffects(enemy)

        assertTrue(bullet.shouldRemove() && enemy.shouldRemove())
    }
}