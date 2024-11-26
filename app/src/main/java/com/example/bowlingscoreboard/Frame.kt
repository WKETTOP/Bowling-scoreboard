package com.example.bowlingscoreboard

class Frame(val firstThrow: Int, val secondThrow: Int) {

    val score: Int
        get() = firstThrow + secondThrow

    val isStrike: Boolean
        get() = firstThrow == 10

    val isSpare: Boolean
        get() =  !isStrike && score == 10

}
