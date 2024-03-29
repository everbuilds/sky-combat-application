package com.skycombat.game.multiplayer

import android.util.Log
import com.skycombat.game.model.gui.element.ghost.Ghost

class LocalOpponentsUpdaterService (private var opponents : List<Ghost>) : OpponentsUpdaterService(){
    private var elapsedTime : Long = 0
    private var alive = true
    override fun getOpponents(): List<Ghost> {
        return opponents
    }

    override fun run() {
        super.run()
        Log.e("debug", "PARTITO THREAD OPPONENTS LOCALE MOCKATO")
        elapsedTime = System.currentTimeMillis()
        super.run()
        while(alive){
            opponents.forEach{ op ->
                if(!op.isDead()) {
                    op.aimedPositionX = Math.random().toFloat() * op.displayDimension.width
                    if(Math.random() < 0.8){
                        op.kill()
                    }
                }
            }
            sleep(10000L / MultiplayerSession.UPS)
        }
    }
    override fun stopUpdates(){
        this.alive = false
    }
}