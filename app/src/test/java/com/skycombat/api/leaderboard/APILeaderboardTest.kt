package com.skycombat.api.leaderboard

import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test

class APILeaderboardTest{

    @Test
    fun `JSON All In One`() {


        val jsonTest = JSONObject(
            """{
            "me" : {
                "username" : "TestLord",
                "score" : 9000,
                "defeated" : 1337,

                "pos" : {
                    "score" : 404,
                    "defeated" : 12321
                }
            },

            "leaderboard" : [
                { "username" : "A", "score" : 1, "defeated" : 5 },
                { "username" : "B", "score" : 2, "defeated" : 4 },
                { "username" : "C", "score" : 3, "defeated" : 3 },
                { "username" : "D", "score" : 4, "defeated" : 2 },
                { "username" : "E", "score" : 5, "defeated" : 1 }
            ]
        }"""
        )
//        jsonTest : JSONObject = Mockito.mock(JSONObject::class.java, Mockito.anyString())

        val obJson = Leaderboard(jsonTest)
        var meChek = false
        var posChek = false
        var leaderboardChek = false

        if (obJson.me?.username == "TestLord" && obJson.me?.score == 9000L && obJson.me?.defeated == 1337L)
            meChek = true

        if (obJson.me?.pos?.score == 404L && obJson.me?.pos?.defeated == 12321L)
            posChek = true

        var alpha = 'A'
        for (i in 0..4) {
            if (obJson.results[i].username == alpha.toString() && obJson.results[i].score == i.toLong()+1 && obJson.results[i].defeated == 5L - i)
                leaderboardChek = true
            else {
                leaderboardChek = false
                break
            }
            alpha++
        }

        assertTrue(meChek && posChek && leaderboardChek)
    }
}
