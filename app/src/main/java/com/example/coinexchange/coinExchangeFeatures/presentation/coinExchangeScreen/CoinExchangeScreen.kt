package com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen.component.CurrencyRow

@Composable
fun CoinExchangeScreen(
//    viewModel: CoinExchangeViewModel = viewModel()
) {

//    val state = viewModel.state



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Coin Exchange",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive,
            color = Color.Blue.copy(alpha = 1f)
        )

        Box(contentAlignment = Alignment.Center){
            Column {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {

                        CurrencyRow(
                            currencyCode = "INR",
                            currencyName = "INDIA",
                            onDropDownClick = { }
                        )

                        Text(
                            text = "20.0",
                            fontSize = 40.sp,
                            color = Color.Red,
                            modifier = Modifier
                                .clickable {  }
                        )

                    }

                }

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {

                        CurrencyRow(
                            currencyCode = "USD",
                            currencyName = "AMERICA",
                            onDropDownClick = { }
                        )

                        Text(
                            text = "2.0",
                            fontSize = 40.sp,
                            color = Color.Red,
                            modifier = Modifier
                                .clickable {  }
                        )

                    }



                }


            }
        }

    }

}

@Preview
@Composable
fun Preview() {

    CoinExchangeScreen()

}