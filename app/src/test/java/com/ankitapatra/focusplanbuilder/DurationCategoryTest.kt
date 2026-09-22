package com.ankitapatra.focusplanbuilder

import com.ankitapatra.focusplanbuilder.model.durationCategory
import org.junit.Assert.assertEquals
import org.junit.Test

class DurationCategoryTest {

    @Test
    fun durationBelowTen_returnsInvalid() {
        assertEquals("Invalid", durationCategory(9))
    }

    @Test
    fun durationFromTenToTwentyNine_returnsQuickReview() {
        assertEquals("Quick review", durationCategory(10))
        assertEquals("Quick review", durationCategory(29))
    }

    @Test
    fun durationFromThirtyToSixty_returnsFocusedSession() {
        assertEquals("Focused session", durationCategory(30))
        assertEquals("Focused session", durationCategory(60))
    }

    @Test
    fun durationAboveSixty_returnsExtendedSession() {
        assertEquals("Extended session", durationCategory(61))
        assertEquals("Extended session", durationCategory(180))
    }
}