package com.org.rickandmorty.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.org.rickandmorty.di.Graph
import com.org.rickandmorty.di.GraphLifecycleCollector
import com.org.rickandmorty.di.graphViewModel
import com.org.rickandmorty.domain.repository.AuthRepository
import com.org.rickandmorty.ui.theme.RickAndMortyTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val authRepo by lazy {
        Graph.get<AuthRepository>(key = "auth_repository")
            ?: error("MainActivity creation failed: AuthRepository not found")
    }

    private val viewModel by graphViewModel { MainViewModel(authRepo::getSessionState) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            enableEdgeToEdge()
            setKeepOnScreenCondition {
                !viewModel.isAuthenticated.value
            }
        }

        GraphLifecycleCollector.init(lifecycle)

        setContent {
            RickAndMortyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${name}!",
        modifier = modifier
    )
}
