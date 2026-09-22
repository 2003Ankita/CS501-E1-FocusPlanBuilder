package com.ankitapatra.focusplanbuilder.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankitapatra.focusplanbuilder.model.FocusPlan
import com.ankitapatra.focusplanbuilder.ui.theme.FocusGreenDark
import com.ankitapatra.focusplanbuilder.ui.theme.FocusGreenLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusSurface
import com.ankitapatra.focusplanbuilder.ui.theme.FocusTextPrimary
import com.ankitapatra.focusplanbuilder.ui.theme.FocusTextSecondary

@Composable
fun FocusPlanResultCard(
    plan: FocusPlan
) {
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

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = plan.subject,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = FocusTextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

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

            Spacer(modifier = Modifier.height(12.dp))

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