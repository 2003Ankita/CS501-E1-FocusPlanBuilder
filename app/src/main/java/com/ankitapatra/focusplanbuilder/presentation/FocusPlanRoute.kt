package com.ankitapatra.focusplanbuilder.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ankitapatra.focusplanbuilder.model.FocusPlan
import com.ankitapatra.focusplanbuilder.model.durationCategory
import com.ankitapatra.focusplanbuilder.model.recommendedBreak

@Composable
fun FocusPlanRoute(
    modifier: Modifier = Modifier
) {
    var subject by rememberSaveable {
        mutableStateOf("")
    }

    var minutesText by rememberSaveable {
        mutableStateOf("")
    }

    var plan by remember {
        mutableStateOf<FocusPlan?>(null)
    }

    val minutes: Int? = minutesText.toIntOrNull()

    val canCreatePlan =
        subject.isNotBlank() &&
                minutes != null &&
                minutes in 10..180

    FocusPlanScreen(
        subject = subject,
        minutesText = minutesText,
        plan = plan,
        onSubjectChange = { updatedSubject ->
            subject = updatedSubject
            plan = null
        },
        onMinutesChange = { updatedMinutesText ->
            plan = null
            minutesText = updatedMinutesText
        },
        canCreatePlan = canCreatePlan,
        onCreatePlan = {
            if (canCreatePlan && minutes != null) {
                plan = FocusPlan(
                    subject = subject.trim(),
                    minutes = minutes,
                    category = durationCategory(minutes),
                    breakMinutes = recommendedBreak(minutes)
                )
            }
        },
        modifier = modifier
    )
}