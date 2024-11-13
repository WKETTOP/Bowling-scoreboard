package com.example.bowlingscoreboard

class Game(private val playersName: String = "") {
    private val frames = mutableListOf<Frame>()

    fun addFrame(firstThrow: Int, secondThrow: Int) {
        frames.add(Frame(firstThrow, secondThrow))
    }

    private fun calculateTotalScore(): List<Int> {
        val scores = mutableListOf<Int>()
        var totalScore = 0

        for (i in frames.indices) {
            val frame = frames[i]
            totalScore += frame.score()

            if (frame.isStrike()) {
                if (i + 1 < frames.size) {
                    totalScore += frames[i + 1].firstThrow
                    if (i + 1 < frames.size - 1) {
                        totalScore += frames[i + 1].secondThrow
                    }
                }
            }

            if (frame.isSpare() && i < frames.size - 1) {
                totalScore += frames[i + 1].firstThrow
            }
            scores.add(totalScore)
        }

        return scores
    }

    fun displayScoreboard(): String {
        val playerRow = StringBuilder("$playersName\t")
        val rollsRow = StringBuilder("Броски\t")
        val scoreRow = StringBuilder("Счет\t")

        val scores = calculateTotalScore()

        for (i in frames.indices) {
            val frame = frames[i]

            playerRow.append(" ${i + 1}\t")

            if (frame.isStrike()) {
                rollsRow.append("X ")
                rollsRow.append(" ")
            } else {
                rollsRow.append("${frame.firstThrow} ")
                rollsRow.append("${if (frame.firstThrow + frame.secondThrow == 10) "/" else frame.secondThrow} ")
            }

            scoreRow.append(" ${scores[i]}\t")
        }

        return "\n$playerRow\n$rollsRow\n$scoreRow"
    }
}
