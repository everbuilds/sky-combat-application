package com.skycombat.game.model.gui.element.enemy

import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.RectF
import com.skycombat.game.model.factory.bullet.BulletFactory
import com.skycombat.game.model.geometry.Rectangle
import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.DrawVisitor
import com.skycombat.game.model.gui.Weapon
import com.skycombat.game.model.gui.component.EnemyHealthBar
import com.skycombat.game.model.gui.component.HealthBar
import com.skycombat.game.model.gui.element.GUIElement
import com.skycombat.game.model.gui.element.bullet.Bullet
import com.skycombat.game.model.gui.element.bullet.collision.EnemyCollisionStrategy
import com.skycombat.game.model.gui.element.enemy.movement.Movement
import com.skycombat.game.model.gui.event.ShootObservable
import com.skycombat.game.model.gui.properties.CanShoot
import com.skycombat.game.model.gui.properties.HasHealth

/**
 * Represents an Enemy
 */
abstract class Enemy(bulletFactory: BulletFactory, val movement: Movement, val displayDimension : DisplayDimension)
    : HasHealth, Rectangle, GUIElement, CanShoot {
    abstract var enemyImg : Int
    var left: Float = -100f
    var top: Float = -100f
    var points : Long = 100
    override var shootObservable = ShootObservable()
    override var weapon: Weapon = Weapon(this, bulletFactory, EnemyCollisionStrategy(), Bullet.Direction.DOWN, displayDimension)
    var healthBar : HealthBar = EnemyHealthBar(this)

    override var health : Float = this.getMaxHealth()


    /**
     * Draws the player and enemy's healthbar
     * @param canvas : the canvas onto which the enemy will be drawn
     * @see HealthBar
     */
    override fun draw(canvas: Canvas?, visitor: DrawVisitor) {
        visitor.draw(canvas, this)
        healthBar.draw(canvas, visitor)
    }

    /**
     * Update bullets to the enemy
     */
    override fun update() {

        movement.move(this)

        healthBar.update()

        weapon.update()
    }

    abstract fun getWidth():Float

    abstract fun getHeight():Float

    override fun shouldRemove(): Boolean {
        return isDead() || left < -150f || top > displayDimension.height
                || left > displayDimension.width|| top <-150f
    }

    override fun getPosition(): RectF {
        return RectF(left, top, left + getWidth() , top + getHeight())
    }

    override fun startPointOfShoot(): PointF {
        return PointF(getPosition().centerX(), top + getHeight() + 2F)
    }


}