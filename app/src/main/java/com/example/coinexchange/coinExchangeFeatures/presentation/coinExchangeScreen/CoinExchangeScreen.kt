package com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinexchange.R
import com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen.component.BottomSheetContent
import com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen.component.CurrencyRow
import com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen.component.KeyboardButton
import com.example.coinexchange.ui.theme.MediumGray
import com.example.coinexchange.ui.theme.Orange
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinExchangeScreen(
    state: CoinExchangeState,
    onEvent: (CoinExchangeEvent) -> Unit
) {


    val keys = listOf("7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "C")

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var shouldBottomSheetShow by remember {
        mutableStateOf(false)
    }

    if (shouldBottomSheetShow){
        ModalBottomSheet(
            onDismissRequest = { shouldBottomSheetShow = false },
            dragHandle = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BottomSheetDefaults.DragHandle()
                    Text(
                       text = "Select Currency",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider()
                }
            },
            content = {
                BottomSheetContent(
                    onItemClick = { currencyCode ->
                        onEvent(CoinExchangeEvent.BottomSheetItemClick(currencyCode))
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) shouldBottomSheetShow = false
                        }
                    },
                    currencyList = state.currencyRates.values.toList()
                )
            }
        )
    }


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

        Box(contentAlignment = Alignment.CenterStart){
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
                            currencyCode = state.fromCurrencyCode,
                            currencyName = state.currencyRates[state.fromCurrencyCode]?.name ?: "",
                            onDropDownClick = {
                                shouldBottomSheetShow = true
                                onEvent(CoinExchangeEvent.FromCurrencySelect)
                            }
                        )

                        Text(
                            text = state.fromCurrencyValue,
                            fontSize = 40.sp,
                            color = Color.Red,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = {
                                        onEvent(CoinExchangeEvent.FromCurrencySelect)
                                    }
                                )
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
                            currencyCode = state.toCurrencyCode,
                            currencyName = state.currencyRates[state.toCurrencyCode]?.name ?: "",
                            onDropDownClick = {
                                shouldBottomSheetShow = true
                                onEvent(CoinExchangeEvent.ToCurrencySelect)
                            }
                        )

                        Text(
                            text = state.toCurrencyValue,
                            fontSize = 40.sp,
                            color = Color.Red,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = {
                                        onEvent(CoinExchangeEvent.ToCurrencySelect)
                                    }
                                )
                        )

                    }

                }

            }

            Box(
                modifier = Modifier
                    .padding(start = 40.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.background)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            onEvent(CoinExchangeEvent.SwapButtonClick)
                        }
                    )
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_sync_24),
                    contentDescription = "Swap coins",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

        }

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 30.dp),
            columns = GridCells.Fixed(3),
        ){
            items(keys){ key ->
                KeyboardButton(
                    modifier = Modifier.aspectRatio(1f),
                    key = key,
                    backgroundColor = if (key == "C") Orange else MediumGray,
                    onKeyClick = {
                        onEvent(CoinExchangeEvent.NumberButtonClick(key))
                    }
                )
            }
        }



    }

}

