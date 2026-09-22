package com.ankitapatra.focusplanbuilder.presentation.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurple
import com.ankitapatra.focusplanbuilder.ui.theme.FocusPurpleLight
import com.ankitapatra.focusplanbuilder.ui.theme.FocusTextSecondary

@Composable
fun FocusPlanHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = FocusPurpleLight
            )
        ) {
            Text(
                text = "📚🌱",
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 12.dp
                ),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Focus Plan Builder",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = FocusPurple
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Build your perfect study session.\nOne focused session at a time.",
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        color = FocusTextSecondary,
        textAlign = TextAlign.Center
    )
}