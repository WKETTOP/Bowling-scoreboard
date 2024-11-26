package com.example.bowlingscoreboard

class Game(private val playersName: String = "") {
    private val frames = mutableListOf<Frame>()

    fun addFrame(firstThrow: Int, secondThrow: Int) {
        frames.add(Frame(firstThrow, secondThrow))
    }

    private fun calculateTotalScore(): List<Int> {
        val scores = mutableListOf<Int>()
        var totalScore = 0


        frames.forEachIndexed { index, currentFrame ->
            totalScore += currentFrame.score


            if (currentFrame.isStrike) {
                val nextFrame = frames.getOrNull(index + 1)

                totalScore += nextFrame?.let { it.firstThrow + (frames.getOrNull(index + 2)?.firstThrow ?: 0) } ?: 0
            }

            if (currentFrame.isSpare) {
                val nextFrame = frames.getOrNull(index + 1)

                totalScore += nextFrame?.firstThrow ?: 0
            }
            scores.add(totalScore)
        }

        return scores
    }

    fun displayScoreboard(): String {
        val lineSeparator = System.lineSeparator()
        var playerRow = "$playersName\t"
        var rollsRow = "Броски\t"
        var scoreRow = "Счет\t"

        val scores = calculateTotalScore()

        frames.forEachIndexed { index, frame ->

            playerRow += " ${index + 1}\t"

            if (frame.isStrike) {
                rollsRow += "X "
                rollsRow += " "
            } else {
                rollsRow += "${frame.firstThrow} "
                rollsRow += "${if (frame.firstThrow + frame.secondThrow == 10) "/" else frame.secondThrow} "
            }

            scoreRow += " ${scores[index]}\t"
        }

        return "$lineSeparator$playerRow$lineSeparator$rollsRow$lineSeparator$scoreRow"
    }
}
