package com.example.showcaseapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.example.showcaseapplication.ui.theme.ShowcaseApplicationTheme
import kotlinx.coroutines.isActive

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowcaseApplicationTheme {
                SideEffectsExample(MyViewModel())
            }
        }
    }
}


@Composable
fun SideEffectsExample(viewModel: MyViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var listenerValue by remember { mutableStateOf("No Listener Value") }

    // TODO: wykonaj akcję wewnątrz wybranego effectu
    LaunchedEffect(listenerValue) {
        viewModel.fetchData()
    }

    // TODO: wykonaj akcję wewnątrz wybranego effectu
    DisposableEffect(Unit) {
        val listener = object : Listener {
            override fun onEvent(event: String) {
                listenerValue = event
            }
        }

        ListenerManager.addListener(listener)

        onDispose {
            ListenerManager.removeListener(listener)
        }
    }



    // TODO: wykonaj akcję wewnątrz wybranego effectu
    SideEffect {
        println("Recomposition happened! Current UI State: $uiState")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("UI State: $uiState", style = MaterialTheme.typography.bodyLarge)
        Text("Listener Value: $listenerValue", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { viewModel.simulateStateChange() }) {
            Text("Simulate State Change")
        }
    }
}
()