package com.googleplaystore.spoonfed.util

import java.math.BigDecimal

object DoubleConvertToFraction {

    fun convertToFraction(d: Double): String {

        val bigDecimal = BigDecimal(d)
        val intValue: Int = bigDecimal.toInt()
        var intString: String = intValue.toString()
        if(intValue == 0){
            intString = ""
        }


        val fractionString: String = when (bigDecimal.subtract(BigDecimal(intValue)).toDouble()) {
            in 0.01..0.15 -> "&#8539" // 1/8
            in 0.15..0.25 -> "&#188" // 1/4
            in 0.25..0.33 -> "&#8531" // 1/3
            in 0.33..0.50 -> "&#189" // 1/2
            in 0.50..0.66 -> "&#8532" // 2/3
            in 0.66..0.75 -> "&#190" // 3/4
            else -> ""
        }
        return "$intString$fractionString"

    }
}