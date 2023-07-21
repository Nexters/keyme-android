package com.keyme.presentation.alarm.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.navigation.KeymeNavigationDestination

object AlarmDestination : KeymeNavigationDestination {
    override val route = "alarm_route"
    override val destination = "alarm_destination"
}

fun NavGraphBuilder.alarmGraph(onBackClick: () -> Unit) {
    composable(route = AlarmDestination.route) {
        AlarmRoute(onBackClick)
    }
}

@Composable
fun AlarmRoute(
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() },
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Text(text = "Alarm", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
