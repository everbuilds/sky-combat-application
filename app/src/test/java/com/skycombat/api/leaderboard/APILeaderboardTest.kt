package com.skycombat.api.leaderboard

import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class APILeaderboardTest{

    @Test
    fun `JSON All In One`() {


        var JsonTest = JSONObject(
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
//        JsonTest : JSONObject = Mockito.mock(JSONObject::class.java, Mockito.anyString())

        val objson = Leaderboard(JsonTest)
        var meChek = false;
        var posChek = false;
        var leaderboardChek = false

        if (objson.me?.username == "TestLord" && objson.me?.score == 9000L && objson.me?.defeated == 1337L)
            meChek = true

        if (objson.me?.pos?.score == 404L && objson.me?.pos?.defeated == 12321L)
            posChek = true

        var alpha = 'A'
        for (i in 0..4) {
            if (objson.results[i].username == alpha.toString() && objson.results[i].score == i.toLong()+1 && objson.results[i].defeated == 5L - i)
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
