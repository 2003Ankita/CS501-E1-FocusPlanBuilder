package com.ankitapatra.focusplanbuilder

import com.ankitapatra.focusplanbuilder.model.recommendedBreak
import org.junit.Assert.assertEquals
import org.junit.Test

class RecommendedBreakTest {

    @Test
    fun quickReviewDuration_returnsFiveMinuteBreak() {
        assertEquals(5, recommendedBreak(10))
        assertEquals(5, recommendedBreak(29))
    }

    @Test
    fun focusedSessionDuration_returnsTenMinuteBreak() {
        assertEquals(10, recommendedBreak(30))
        assertEquals(10, recommendedBreak(60))
    }

    @Test
    fun extendedSessionDuration_returnsFifteenMinuteBreak() {
        assertEquals(15, recommendedBreak(61))
        assertEquals(15, recommendedBreak(180))
    }

    @Test
    fun invalidDuration_returnsZero() {
        assertEquals(0, recommendedBreak(9))
    }
}