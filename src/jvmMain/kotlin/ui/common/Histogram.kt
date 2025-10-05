package ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.char.CharInfo

@Composable
fun Histogram(
    charList: List<CharInfo>,
    onStringChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val rowHeight = constraints.maxHeight
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            charList.forEach { charInfo ->
                val interactionSource = remember { MutableInteractionSource() }
                val isHovered by interactionSource.collectIsHoveredAsState()
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .hoverable(interactionSource = interactionSource)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Row(
                        modifier = Modifier
                            .height((charInfo.fmp * rowHeight).dp)
                            .fillMaxWidth()
                            .background(Color.Blue)
                    ) {}
                    if (isHovered) {
                        onStringChange(
                            "Character: ${charInfo.char} | Count: ${charInfo.occ} | FMP: ${
                                "%.4f".format(
                                    charInfo.fmp
                                )
                            }"
                        )
                    }
                }
            }
        }
    }
}