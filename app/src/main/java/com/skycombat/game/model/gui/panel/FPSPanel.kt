package com.skycombat.game.model.gui.panel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.skycombat.game.model.ViewContext
import com.skycombat.game.scene.GameLoop
import com.skycombat.game.scene.GameView

class FPSPanel(var x: Float, var y: Float, var gameLoop : GameLoop, var scene : GameView) : GamePanel {
    var context: ViewContext = ViewContext.getInstance()
    override fun draw(canvas: Canvas?) {
        val avg: String = gameLoop.getAverageFPS().toInt().toString()
        val paint = Paint()
        paint.color = Color.RED
        paint.textSize = 50F
        canvas?.drawText("FPS: $avg", x, y, paint)
    }

}