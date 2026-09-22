package com.ankitapatra.focusplanbuilder.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ankitapatra.focusplanbuilder.model.durationCategory
import com.ankitapatra.focusplanbuilder.model.recommendedBreak
import com.ankitapatra.focusplanbuilder.ui.theme.FocusError
import com.ankitapatra.focusplanbuilder.ui.theme.FocusGreenDark
import com.ankitapatra.focusplanbuilder.ui.theme.FocusGreenLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPeach
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPink
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleDark
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusSurface
import com.ankitapatra.focusplanbuilder.ui.theme.FocusTextSecondary

@Composable
fun PlanInputCard(
    subject: String,
    minutesText: String,
    onSubjectChange: (String) -> Unit,
    onMinutesChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = FocusPeach
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = FocusPurpleLight
                )
            ) {
                Text(
                    text = "📚  What are you studying?",
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 6.dp
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FocusPurpleDark
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = subject,
                onValueChange = onSubjectChange,
                label = {
                    Text("Study subject")
                },
                placeholder = {
                    Text("e.g., Kotlin, Databases, Compose state")
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = FocusSurface,
                    unfocusedContainerColor = FocusSurface,
                    disabledContainerColor = FocusSurface
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = FocusGreenLight
                )
            ) {
                Text(
                    text = "⏱️  How much time do you have?",
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 6.dp
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FocusGreenDark
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = minutesText,
                onValueChange = onMinutesChange,
                label = {
                    Text("Available minutes")
                },
                placeholder = {
                    Text("10–180")
                },
                suffix = {
                    Text(
                        text = "min",
                        color = FocusTextSecondary
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = FocusSurface,
                    unfocusedContainerColor = FocusSurface,
                    disabledContainerColor = FocusSurface
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Choose a study duration from 10 to 180 minutes.",
                style = MaterialTheme.typography.bodySmall,
                color = FocusTextSecondary
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = FocusPink
                )
            ) {
                Text(
                    text = "⚡  Quick picks",
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 6.dp
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FocusPurpleDark
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            QuickPickButtons(
                minutesText = minutesText,
                onMinutesChange = onMinutesChange
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            if (minutesText.isNotBlank()) {
                val enteredMinutes = minutesText.toIntOrNull()

                Text(
                    text = when {
                        enteredMinutes == null ->
                            "Enter a whole number between 10 and 180."

                        enteredMinutes !in 10..180 ->
                            "Study time must be between 10 and 180 minutes."

                        else ->
                            "${durationCategory(enteredMinutes)} • ${
                                recommendedBreak(enteredMinutes)
                            } min recommended break"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = if (
                        enteredMinutes != null &&
                        enteredMinutes in 10..180
                    ) {
                        FocusGreenDark
                    } else {
                        FocusError
                    }
                )
            }
        }
    }
}