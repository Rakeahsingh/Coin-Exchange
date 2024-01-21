package com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinexchange.coinExchangeFeatures.domain.model.CurrencyRate

@Composable
fun BottomSheetContent(
    onItemClick: (String) -> Unit,
    currencyList: List<CurrencyRate>
) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
    ){
        items(currencyList){ currency ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 20.dp)
                    .clickable { onItemClick(currency.code) }
            ){
                Text(
                    text = "${currency.code} :  ${currency.name}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }

}