package com.example.bowlingscoreboard

class Frame(val firstThrow: Int, val secondThrow: Int) {

    fun score(): Int {
        return firstThrow + secondThrow
    }

    fun isStrike(): Boolean {
        return firstThrow == 10
    }

    fun isSpare(): Boolean {
        return !isStrike() && score() == 10
    }
}
