package ui.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.char.CharInfo

@Composable
fun CharList(
    charList: List<CharInfo>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(charList.size) {
            Text(charList[it].toString())
        }
    }
}