package com.googleplaystore.spoonfed

import com.googleplaystore.spoonfed.util.DoubleConvertToFraction
import junit.framework.TestCase.assertEquals
import org.junit.Test


class DoubleToFraction {

    @Test
    fun doubleToFraction_CorrectValueSimple_ReturnValue() {
        val value = DoubleConvertToFraction.convertToFraction(2.00)

        assertEquals("The value are not correct",  "2 ", value)
    }

    @Test
    fun doubleToFraction_CorrectValueComplex_ReturnValue() {
        val value = DoubleConvertToFraction.convertToFraction(2.46)

        assertEquals("The value are not correct",  "2 &#189", value)
    }

}