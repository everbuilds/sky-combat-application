package com.skycombat

import android.graphics.RectF
import com.skycombat.game.model.geometry.Rectangle

open class FakeRectangle(var x: Float, var y: Float): Rectangle {
    override fun getPosition(): RectF {
        return RectF(this.x - 140F, this.y - 40F, this.x + 140F, this.y + 40F)
    }
}