package com.ankitapatra.focusplanbuilder

import com.ankitapatra.focusplanbuilder.model.FocusPlan
import org.junit.Assert.assertEquals
import org.junit.Test

class FocusPlanTest {

    @Test
    fun focusPlan_storesAllPlanDetailsCorrectly() {
        val plan = FocusPlan(
            subject = "Kotlin",
            minutes = 60,
            category = "Focused session",
            breakMinutes = 10
        )

        assertEquals("Kotlin", plan.subject)
        assertEquals(60, plan.minutes)
        assertEquals("Focused session", plan.category)
        assertEquals(10, plan.breakMinutes)
    }
}