package com.ankitapatra.focusplanbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ankitapatra.focusplanbuilder.presentation.FocusPlanRoute
import com.ankitapatra.focusplanbuilder.ui.theme.Focus_Plan_BuilderTheme

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