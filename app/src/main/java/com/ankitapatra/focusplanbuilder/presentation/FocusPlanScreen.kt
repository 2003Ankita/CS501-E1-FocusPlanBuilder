package com.ankitapatra.focusplanbuilder.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import com.ankitapatra.focusplanbuilder.presentation.components.PlanInputCard
import androidx.compose.foundation.verticalScroll
import com.ankitapatra.focusplanbuilder.presentation.components.CreatePlanButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ankitapatra.focusplanbuilder.model.FocusPlan
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleDark
import com.ankitapatra.focusplanbuilder.presentation.components.FocusPlanResultCard
import com.ankitapatra.focusplanbuilder.presentation.components.FocusPlanHeader
@Composable
fun FocusPlanScreen(
    subject: String,
    minutesText: String,
    plan: FocusPlan?,
    onSubjectChange: (String) -> Unit,
    onMinutesChange: (String) -> Unit,
    canCreatePlan: Boolean,
    onCreatePlan: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF3EC),
                        Color(0xFFFFF7F4),
                        Color(0xFFF8F0FF),
                        Color(0xFFFFF4EC)
                    )
                )
            )
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        FocusPlanHeader()

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        PlanInputCard(
            subject = subject,
            minutesText = minutesText,
            onSubjectChange = onSubjectChange,
            onMinutesChange = onMinutesChange
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        CreatePlanButton(
            canCreatePlan = canCreatePlan,
            onCreatePlan = onCreatePlan
        )

        if (plan != null) {
            Spacer(
                modifier = Modifier.height(24.dp)
            )

            FocusPlanResultCard(
                plan = plan
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                text = "Small steps.\nBig progress.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = FocusPurpleDark
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}