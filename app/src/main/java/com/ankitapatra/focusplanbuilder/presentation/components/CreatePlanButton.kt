package com.ankitapatra.focusplanbuilder.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurple
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusSurface
import com.ankitapatra.focusplanbuilder.ui.theme.FocusTextSecondary

@Composable
fun CreatePlanButton(
    canCreatePlan: Boolean,
    onCreatePlan: () -> Unit
) {
    Button(
        onClick = onCreatePlan,
        enabled = canCreatePlan,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (canCreatePlan) {
                FocusPurple
            } else {
                FocusPurpleLight
            }
        ),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = FocusPurple,
            contentColor = FocusSurface,
            disabledContainerColor = FocusPurpleLight,
            disabledContentColor = FocusTextSecondary
        )
    ) {
        Text(
            text = "✦  Create plan",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}