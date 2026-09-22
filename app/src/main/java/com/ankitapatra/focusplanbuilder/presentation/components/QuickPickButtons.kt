package com.ankitapatra.focusplanbuilder.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ankitapatra.focusplanbuilder.ui.theme.FocusGreenLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPeach
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPink
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleDark
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusSurface
import com.ankitapatra.focusplanbuilder.ui.theme.FocusTextSecondary

@Composable
fun QuickPickButtons(
    minutesText: String,
    onMinutesChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf(15, 25, 45, 60).forEach { duration ->
            val isSelected = minutesText == duration.toString()

            OutlinedButton(
                onClick = {
                    onMinutesChange(duration.toString())
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isSelected) {
                        FocusPurpleLight
                    } else {
                        when (duration) {
                            15 -> FocusPink
                            25 -> FocusGreenLight
                            45 -> FocusPeach
                            else -> FocusSurface
                        }
                    },
                    contentColor = if (isSelected) {
                        FocusPurpleDark
                    } else {
                        FocusTextSecondary
                    }
                )
            ) {
                Text(text = "$duration")
            }
        }
    }
}