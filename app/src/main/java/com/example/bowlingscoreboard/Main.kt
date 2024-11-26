package com.example.bowlingscoreboard

import kotlin.random.Random

fun main() {
    val games = mutableListOf<Game>()

    println("Введите кол-во игроков: ")
    val numberOfPlayer = readLine()?.toIntOrNull() ?: 1

    for (i in 1..numberOfPlayer) {
        println("Введите имя игрока $i: ")
        val playerName = readLine().orEmpty()

        val game = Game(playerName)

        for (j in 1..10) {
            val firstThrow = Random.nextInt(0, 11)
            val secondThrow = if (firstThrow < 10) Random.nextInt(0, 11 - firstThrow) else 0
            game.addFrame(firstThrow, secondThrow)
        }
        games.add(game)
    }

    println("Результат игры в боуллинг: ")
    games.forEach { game -> println(game.displayScoreboard()) }
}
