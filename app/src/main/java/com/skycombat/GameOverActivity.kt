package com.skycombat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.mobile.client.AWSMobileClient
import com.amplifyframework.core.Amplify
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.nio.charset.Charset

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_game_over)

        val score = intent.getLongExtra(GameActivity.SIGLA_SCORE, 0)
        val gameType : GameActivity.GAMETYPE =
                intent.getSerializableExtra(GameActivity.SIGLA_TYPE)!! as GameActivity.GAMETYPE

        findViewById<ImageButton>(R.id.backToHome).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<TextView>(R.id.score).text = score.toString()
        if(Amplify.Auth.currentUser != null) {
            uploadScore(gameType, score)
        }
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }

    private fun uploadScore(gameType : GameActivity.GAMETYPE, score : Long) {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val jsonBody = JSONObject()
        val url : String
        when (gameType) {
            GameActivity.GAMETYPE.SINGLE_PLAYER -> {
                url = "https://dmh7jq3nqi.execute-api.eu-central-1.amazonaws.com/V1/update-singleplayer-score"
                jsonBody.put("score", score)
            }
            GameActivity.GAMETYPE.MULTI_PLAYER -> {
                url = "https://dmh7jq3nqi.execute-api.eu-central-1.amazonaws.com/V1/update-multiplayer-score"
                jsonBody.put("defeated", score)
            }
        }

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            url,
            { resp -> Log.i("RESP", "Inviato il risultato: $resp") },
            { resp -> Log.wtf("RESP ERRORE", "Errore invio: $resp") }
        ) {
            override fun getBody(): ByteArray {
                return jsonBody.toString().toByteArray(Charset.defaultCharset())
            }
            override fun getHeaders():Map<String, String> {
                return mapOf(
                    "Authorization" to AWSMobileClient.getInstance().tokens.idToken.tokenString
                )
            }
        }
        requestQueue.add(stringRequest)
    }
}