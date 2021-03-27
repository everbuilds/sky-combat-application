package com.skycombat.game.model.factory.enemy

import com.skycombat.game.model.factory.bullet.*
import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.Player
import com.skycombat.game.model.gui.element.enemy.Enemy
import com.skycombat.game.model.gui.element.enemy.JetEnemy
import com.skycombat.game.model.gui.element.enemy.PlaneEnemy
import com.skycombat.game.model.gui.element.enemy.SpaceShipEnemy
import com.skycombat.game.model.gui.element.enemy.movement.Movement
import kotlin.random.Random

class SeedGeneralEnemyFactory(var seed: Long, val displayDimension: DisplayDimension) : GeneralEnemyFactory{

    private val random = Random(seed)
    private var iteration = 0
    /**
     * Generates the enemies
     * @return Enemy to the scene
     * @see Player
     */
    override fun generate(): Enemy {
        iteration++;

        val randMovement : Movement = when(random.nextInt(1,11)){
            1 -> Movement(4,3,600 + iteration * 10)
            2,5 -> Movement(3,4,500 + iteration * 10)
            3,6,8 -> Movement(6,2,700 + iteration * 10)
            4,7,9,10 -> Movement(2,5,400 + iteration * 10)
            else -> Movement(2,5,400 + iteration * 10)
        }

        val bulletFactory: BulletFactory = when (random.nextInt(1, 11)) {
            1,5,8,10 -> LaserBulletFactory()
            2,6,9 -> MultipleBulletFactory()
            3,7 -> GustBulletFactory()
            4 -> ClassicBulletFactory()
            else -> ClassicBulletFactory()
        }

        return when (random.nextInt(1,7)) {
            1 -> PlaneEnemy(bulletFactory, randMovement,displayDimension, 1 + (iteration.toFloat() / 3))
            2,4 -> JetEnemy(bulletFactory, randMovement,displayDimension)
            3,5,6 -> SpaceShipEnemy(bulletFactory, randMovement,displayDimension)
            else-> SpaceShipEnemy(bulletFactory, randMovement,displayDimension)
        }
    }
}