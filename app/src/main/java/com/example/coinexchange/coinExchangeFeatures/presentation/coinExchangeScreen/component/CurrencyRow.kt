package com.example.coinexchange.coinExchangeFeatures.presentation.coinExchangeScreen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CurrencyRow(
    modifier: Modifier = Modifier,
    currencyCode: String,
    currencyName: String,
    onDropDownClick: () -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = currencyCode,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = { onDropDownClick() }
        ) {
           Icon(
               imageVector = Icons.Default.ArrowDropDown,
               contentDescription = "drop down menu"
           )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = currencyName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

    }

}