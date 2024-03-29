package com.skycombat.game.model.gui.element.bullet

import com.skycombat.game.model.geometry.Entity
import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.GUIElement
import com.skycombat.game.model.gui.element.bullet.collision.CollisionStrategy
import com.skycombat.game.model.gui.properties.HasHealth

/**
 * Represents a bullet
 * @param x : axis x coordinate of the bullet
 * @param y : axis y coordinate of the bullet
 * @param direction : direction of the bullet
 */
abstract class Bullet(var x : Float, var y : Float, var collisionStrategy: CollisionStrategy, private val direction: Direction, val displayDimension : DisplayDimension)
    : GUIElement, Entity {

    enum class Direction{
        UP {
            override fun apply(delta: Float) : Float {
                return delta * -1
            }
        }, DOWN {
            override fun apply(delta: Float) : Float {
                return delta
            }
        };
        abstract fun apply(delta: Float) : Float
    }


    private var isHit: Boolean = false

    /**
     * Updates the bullet's movement
     */
    override fun update() {
        y += direction.apply(getSpeed())
    }

    /**
     * Removes if hit gets object out of context
     * @return isHit || this.y < 0 || this.y > scene.getMaxHeight()
     */
    override fun shouldRemove(): Boolean {
        return isHit || this.y < 0 || this.y > displayDimension.height
    }

    abstract fun getDamage(): Float
    abstract fun getSpeed(): Float

    fun applyCollisionEffects(entityHit: HasHealth) {
        if (collisionStrategy.shouldCollide(entityHit)) {
            isHit = true
            entityHit.updateHealth(-getDamage())
        }
    }
}