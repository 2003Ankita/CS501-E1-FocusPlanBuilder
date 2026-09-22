package com.ankitapatra.focusplanbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ankitapatra.focusplanbuilder.ui.theme.Focus_Plan_BuilderTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.statusBarsPadding
import com.ankitapatra.focusplanbuilder.ui.theme.FocusBackground
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurple
import com.ankitapatra.focusplanbuilder.ui.theme.FocusTextSecondary
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import com.ankitapatra.focusplanbuilder.ui.theme.FocusSurface
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleDark
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusGreenLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusTextPrimary
import com.ankitapatra.focusplanbuilder.ui.theme.FocusGreenDark
import com.ankitapatra.focusplanbuilder.ui.theme.FocusError
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Focus_Plan_BuilderTheme {
                FocusPlanRoute()
            }
        }
    }
}

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
            .background(FocusBackground)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Text(
            text = "Focus Plan Builder",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = FocusPurple
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Build your perfect study session.\nOne focused session at a time.",
            style = MaterialTheme.typography.bodyLarge,
            color = FocusTextSecondary
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = FocusSurface
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "What are you studying?",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FocusPurple
                )

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
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Text(
                    text = "How much time do you have?",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FocusPurple
                )

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

                Text(
                    text = "Quick picks",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FocusTextSecondary
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )

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
                                    FocusSurface
                                },
                                contentColor = if (isSelected) {
                                    FocusPurpleDark
                                } else {
                                    FocusTextSecondary
                                }
                            )
                        ) {
                            Text(
                                text = "$duration"
                            )
                        }
                    }
                }
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
                                "${durationCategory(enteredMinutes)} • ${recommendedBreak(enteredMinutes)} min recommended break"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = if (enteredMinutes != null && enteredMinutes in 10..180) {
                            FocusGreenDark
                        } else {
                            FocusError
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = onCreatePlan,
            enabled = canCreatePlan,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = FocusPurple,
                contentColor = FocusSurface,
                disabledContainerColor = FocusPurpleLight,
                disabledContentColor = FocusTextSecondary
            )
        ) {
            Text(
                text = "Create plan",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        if (plan != null) {
            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = FocusGreenLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "YOUR FOCUS PLAN",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = FocusGreenDark
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = plan.subject,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = FocusTextPrimary
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Duration",
                                style = MaterialTheme.typography.labelMedium,
                                color = FocusTextSecondary
                            )

                            Text(
                                text = "${plan.minutes} min",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = FocusTextPrimary
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1.5f)
                        ) {
                            Text(
                                text = "Category",
                                style = MaterialTheme.typography.labelMedium,
                                color = FocusTextSecondary
                            )

                            Text(
                                text = plan.category,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = FocusTextPrimary
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Break",
                                style = MaterialTheme.typography.labelMedium,
                                color = FocusTextSecondary
                            )

                            Text(
                                text = "${plan.breakMinutes} min",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = FocusTextPrimary
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = FocusSurface
                        )
                    ) {
                        Text(
                            text = "Study ${plan.subject} for ${plan.minutes} minutes, and then take a ${plan.breakMinutes}-minute break.",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            color = FocusTextPrimary
                        )
                    }
                }
            }
        }
    }
}