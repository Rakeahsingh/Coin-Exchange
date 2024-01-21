package com.example.coinexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen.CoinExchangeScreen
import com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen.CoinExchangeViewModel
import com.example.coinexchange.ui.theme.CoinExchangeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinExchangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel = viewModel<CoinExchangeViewModel>()
                    val state by viewModel.state.collectAsState()

                    CoinExchangeScreen(
                        state = state,
                        onEvent = viewModel::onEvent
                    )

                }
            }
        }
    }
}

