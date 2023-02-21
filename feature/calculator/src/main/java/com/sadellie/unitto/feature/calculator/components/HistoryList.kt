/*
 * Unitto is a unit converter for Android
 * Copyright (c) 2023 Elshan Agaev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sadellie.unitto.feature.calculator.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sadellie.unitto.core.ui.theme.NumbersTextStyleDisplayMedium
import com.sadellie.unitto.data.model.HistoryItem
import java.text.SimpleDateFormat
import java.util.*

@Composable
internal fun HistoryList(
    modifier: Modifier,
    historyItems: List<HistoryItem>,
    historyItemHeightCallback: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(historyItems) { historyItem ->
            Column(
                Modifier.onPlaced { historyItemHeightCallback(it.size.height) }
            ) {
                Text(
                    text = historyItem.expression,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState(), reverseScrolling = true),
                    style = NumbersTextStyleDisplayMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.End
                )
                Text(
                    text = historyItem.result,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState(), reverseScrolling = true),
                    style = NumbersTextStyleDisplayMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryList() {
    val dtf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())

    val historyItems = listOf(
        "13.06.1989 23:59:15",
        "13.06.1989 23:59:16",
        "13.06.1989 23:59:17",
        "14.06.1989 23:59:17",
        "14.06.1989 23:59:18",
        "14.07.1989 23:59:18",
        "14.07.1989 23:59:19",
        "14.07.2005 23:59:19",
    ).map {
        HistoryItem(
            date = dtf.parse(it)!!,
            expression = "12345".repeat(10),
            result = "67890"
        )
    }

    HistoryList(
        modifier = Modifier.fillMaxWidth(),
        historyItems = historyItems,
        historyItemHeightCallback = {}
    )
}
