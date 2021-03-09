package com.skycombat.game.model.component

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.skycombat.game.model.HasHealth
import com.skycombat.game.model.Player
import com.skycombat.game.model.ViewContext

class PlayerHealthBar(var element : Player): HealthBar() {

    var context: ViewContext = ViewContext.getInstance()
    val initialLife:RectF

    init{
        life=RectF(20f, context.getHeightScreen() - 50, context.getWidthScreen()-20, context.getHeightScreen() - 10)
        initialLife = RectF(life)
    }

    override fun update() {
        percentage = element.getCurrentHealth() / element.getCurrentHealth().coerceAtLeast(element.getMaxHealth())
        life.right = initialLife.left + (initialLife.right - initialLife.left) * percentage
    }
}