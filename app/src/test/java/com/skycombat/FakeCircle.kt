package com.skycombat

import android.graphics.PointF
import com.skycombat.game.model.geometry.Circle

open class FakeCircle(var x: Float, var y: Float) : Circle {
    override fun getCenter(): PointF {
        return PointF(this.x, this.y)
    }

    override fun getRadius(): Float {
        return 70F
    }
}