package com.skycombat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.skycombat.game.model.gui.DisplayDimension
import com.skycombat.game.model.gui.element.Player
import com.skycombat.game.model.gui.element.ghost.Ghost
import com.skycombat.game.model.gui.element.ghost.movement.LinearAimedPositionMovement
import com.skycombat.game.multiplayer.MultiplayerSession
import com.skycombat.game.multiplayer.OpponentsUpdater
import com.skycombat.game.multiplayer.PlayerUpdaterService
import com.skycombat.game.multiplayer.RemoteOpponentUpdaterService
import com.skycombat.game.scene.GameView
import java.io.Serializable
import java.util.concurrent.CopyOnWriteArrayList
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random

class GameActivity : Activity() {
    companion object{
        const val SIGLA_SCORE = "game-score"
        const val SIGLA_TYPE = "game-type"
    }
    enum class GAMETYPE : Serializable{
        SINGLE_PLAYER {
            override fun sigla(): String {
                return "single-player"
            }
        }, MULTI_PLAYER {
            override fun sigla(): String {
                return "multi-player"
            }
        };
        abstract fun sigla(): String
    }

    //gameView will be the mainview and it will manage the game's logic
    private val velocity = 4f
    private var gameView: GameView? = null
    private var opponentsUpdater : OpponentsUpdater? = null
    private var remotePlayer: PlayerUpdaterService? = null
    private var currentGametype : GAMETYPE = GAMETYPE.SINGLE_PLAYER
    private lateinit var player : Player
    private lateinit var quitButton : ImageButton
    private var score = 0L

    override fun onCreate(savedInstanceState: Bundle?) {

        // impostare l'activity
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // ottenere dimensioni schermo
        val metrics = DisplayMetrics()
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(metrics)
        val displayDimension = DisplayDimension(
                metrics.widthPixels.toFloat(),
                metrics.heightPixels.toFloat()
        )



        // creazione GameView
        player = Player(velocity, LinearAimedPositionMovement(), displayDimension)
        player.addOnDeathOccurListener{
            score = when(currentGametype){
                GAMETYPE.MULTI_PLAYER -> getCountDeadOpponents()
                GAMETYPE.SINGLE_PLAYER -> gameView?.deadEnemies?.map { enemy ->
                    enemy.points
                }?.reduceOrNull(Long::plus) ?: 0L

            }
            remotePlayer?.setAsDead(getCountDeadOpponents().toInt())
        }
        gameView = GameView(
            this,
            displayDimension,
            player,
            seed = MultiplayerSession.player?.gameroom?.seed?.toLong() ?: Random.nextLong()
        )
        gameView!!.addGameOverListener {
            callGameOverActivity()
        }



        // creazione dinamiche multiplayer
        val ghosts: CopyOnWriteArrayList<Ghost>

        if(MultiplayerSession.player != null) {
            currentGametype = GAMETYPE.MULTI_PLAYER

            // opponenti
            ghosts = CopyOnWriteArrayList(IntStream
                .range(0, MultiplayerSession.opponents.size)
                .mapToObj{ Ghost(LinearAimedPositionMovement(), velocity, displayDimension) }
                .collect(Collectors.toList()))
            opponentsUpdater = RemoteOpponentUpdaterService(
                MultiplayerSession.player!!,
                MultiplayerSession.opponents.zip(ghosts)
            )
            opponentsUpdater?.start()

            // gestiamo il player corrente
            remotePlayer = PlayerUpdaterService(gameView!!.getPlayer(), MultiplayerSession.player!!)
            remotePlayer?.start()

            gameView?.setGhosts(ghosts)

            //aggiunto il listener per l'uscita precoce solo in multiplayer
            gameView!!.addPlayerIsDeadListener {
                showQuit()
            }

            //creazione del FrameLayout per contenere gameView e il bottone di uscita precoce
            var fw : FrameLayout = FrameLayout(this)
            var overlay : RelativeLayout = LayoutInflater.from(this).inflate(R.layout.activity_game_quit, fw, false) as RelativeLayout
            fw.addView(gameView)
            fw.addView(overlay)

            setContentView(fw)


            // imposta onClickListener per uscire prima dalla partita multiplayer
            quitButton = findViewById<ImageButton>(com.skycombat.R.id.quit)
            quitButton.setOnClickListener{
                this.callGameOverActivity()
                this.finish()
            }
            //toglie l'allocazione sulla GUI del bottone cosi` puo` riapparire sopra la gameView
            quitButton.visibility = View.GONE

        } else {
            currentGametype = GAMETYPE.SINGLE_PLAYER
            ghosts = CopyOnWriteArrayList()

            gameView?.setGhosts(ghosts)
            setContentView(gameView)

            /*
            // serve solo per simulare il multiplayer in locale utilizzato la modalità "singleplayer" del gioco
            currentGAMETYPE = GAMETYPE.MULTI_PLAYER
            ghosts = CopyOnWriteArrayList(IntStream
                    .range(0, 4)
                    .mapToObj{ Ghost(LinearAimedPositionMovement(), velocity) }
                    .collect(Collectors.toList()))
            opponentsUpdater = MockOpponentsUpdaterService(ghosts)
            opponentsUpdater?.start()
            */
        }

    }

    private fun getCountDeadOpponents() : Long {
        if(player.isAlive()){
            return opponentsUpdater?.getOpponents()?.count { el ->
                el.isDead()
            }?.toLong() ?: 0L
        }
        return opponentsUpdater?.getOpponents()?.count { el ->
            el.isDead() &&
                    el.deadAt!! <
                    player.deadAt!!
        }?.toLong() ?: 0L
    }

    /**
     * Calls the GameOverActivity to finish the game and report the score
     * @see GameOverActivity
     */
    private fun callGameOverActivity() {
        val intent = Intent(this, GameOverActivity::class.java)

        // in caso ci fossero servizi di aggiornamento remoti, li fermo
        remotePlayer?.setAsDead(getCountDeadOpponents().toInt())
        opponentsUpdater?.stopUpdates()

        intent.putExtra(SIGLA_TYPE, currentGametype)
        intent.putExtra(SIGLA_SCORE, score)

        startActivity(intent)
    }


    private fun showQuit() {
        runOnUiThread{ quitButton.visibility = View.VISIBLE }
    }

    override fun onPause() {
        super.onPause()
        gameView?.pause()
    }

    override fun onStop() {
        super.onStop()
        remotePlayer?.setAsDead(getCountDeadOpponents().toInt())
        opponentsUpdater?.stopUpdates()
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }
}