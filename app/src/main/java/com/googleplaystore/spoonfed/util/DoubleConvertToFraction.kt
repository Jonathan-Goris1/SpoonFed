package com.googleplaystore.spoonfed.util

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow

object DoubleConvertToFraction {

    fun convertToFraction(d: Double): String {
        if(floor(d) == d){
            return d.toInt().toString()
        }
// get the whole number part
        val i = ceil(d).toLong()
        // get the fractional part
        var numerator = d - i
        // Convert the fractional part to a String
        var frac = numerator.toString()
        // We only want what's to the right of the //decimal point
        frac = frac.substring(frac.indexOf('.'))
        // Put the String back into a double
        numerator = frac.toDouble()
        val power = frac.length
        var denominator = 10.0.pow(power.toDouble())
        // implement findGCD()
        val gcd: Int = findGCD(numerator, denominator)
        numerator /= gcd.toDouble()
        denominator /= gcd.toDouble()
        return i.toString() + "-" + numerator.toLong() + "/" + denominator.toLong()
    }

    private fun findGCD(numerator: Double, denominator: Double): Int{
        var n1 = numerator
        var n2 = denominator

        while (n1 != n2) {
            if (n1 > n2)
                n1 -= n2
            else
                n2 -= n1
        }

        return n1.toInt()
    }
}