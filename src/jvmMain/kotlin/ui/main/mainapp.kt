package ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.file.checkFiles
import model.theory.entropy
import model.vals.*
import ui.customComposables.button.customButton
import ui.customComposables.checkbox.customCheckbox

@Composable
fun mainApp(){
    MaterialTheme{
        Column{
            val charList = charMap.toList().sortedBy { (_, value) -> value }.reversed()
            val fmpList = fmpMap.toList().sortedBy { (_, value) -> value }.reversed()
            val selfinfoList = selfinfoMap.toList().sortedBy { (_, value) -> value }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                var path by remember { mutableStateOf("") }
                Row {
                    TextField(
                        value = path,
                        onValueChange = {
                            path = it
                        },
                        modifier = Modifier.width(400.dp),
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Row{
                    customButton(
                        "Find...",
                        { checkFiles(path) },
                        Modifier
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Row {
                    Column {
                        Row {
                            customCheckbox(
                                isCapitalSame,
                                { isCapitalSame = !isCapitalSame },
                                "'E' = 'e' ?",
                                Modifier.align(Alignment.CenterVertically)
                            )
                        }
                        Row {
                            customCheckbox(
                                isSpaceCounted,
                                { isSpaceCounted = !isSpaceCounted },
                                "' ' ?",
                                Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    Column {
                        Row {
                            customCheckbox(
                                isSignalSame,
                                { isSignalSame = !isSignalSame },
                                "'É' = 'E' ?",
                                Modifier.align(Alignment.CenterVertically)
                            )
                        }
                        Row {
                            customCheckbox(
                                isAlphabet,
                                { isAlphabet = !isAlphabet },
                                " ),(,*,... ?",
                                Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Row{
                    Text(
                        text = currFile ?: "No file loaded"
                    )
                }
                Row{
                    Text("${entropy(fmpMap, selfinfoMap)} bits/symbol")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp)
                    .background(Color.LightGray)
                    .border(1.dp, Color.Gray)
            ) {
                LazyColumn {
                    items(charMap.size) {
                        Text("Character ${charList[it].first} is present ${charList[it].second} times," +
                                " will occur %${fmpList[it].second} of the time" +
                                " and has ${selfinfoList[it].second} self information"
                        )
                    }
                }
            }
            var string by remember { mutableStateOf("") }
            Column(modifier = Modifier.padding(4.dp)) { Text(string) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp)
                    .background(Color.LightGray)
                    .border(1.dp, Color.Gray)
            ){
                BoxWithConstraints {
                    val rowHeight = constraints.maxHeight
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        repeat(charMap.keys.size-1) {
                            val interactionSource = remember { MutableInteractionSource() }
                            val isHovered by interactionSource.collectIsHoveredAsState()
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .hoverable(
                                        interactionSource = interactionSource
                                    )
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                val rowHeightProportions = rowHeight / 100
                                Row(
                                    modifier = Modifier
                                        .height(fmpList[it].second.dp * rowHeightProportions)
                                        .fillMaxWidth()
                                        .background(Color.Blue)
                                ) {}
                                if (isHovered) {
                                    string = "You're hovering on the character ${charList[it].first}, " +
                                            "present ${charList[it].second} times."
                                }
                            }
                        }
                    }
                }
                /*
                Row {
                    Canvas(
                        modifier = Modifier
                            .padding(4.dp)
                            .border(1.dp, Color.Gray)
                            .background(Color.LightGray)
                            .fillMaxSize()
                    ){
                        canvasWidth = this.size.width
                        canvasHeight = this.size.height

                        var counter = 0
                        val canvasHeightToHeight = canvasHeight/100

                        fmpMap.forEach{
                            drawRect(
                                color = Color.Blue,
                                topLeft = Offset((canvasWidth / maxWidthHistogram())*counter,canvasHeight-it.value*canvasHeightToHeight),
                                size = Size(canvasWidth / maxWidthHistogram(), it.value * canvasHeightToHeight)
                            )
                            counter += 1
                        }

                        /*
                        for(i in fmpMap){
                            drawRect(
                                color = Color.Blue,
                                topLeft = Offset((canvasWidth / maxWidthHistogram())*counter,canvasHeight-i.value*canvasHeightToHeight),
                                size = Size(canvasWidth / maxWidthHistogram(), i.value * canvasHeightToHeight)
                            )
                            counter += 1
                        }
                        */
                    }
                }

                 */
            }
        }
    }
}