package ui.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.file.checkFiles
import model.theory.entropy
import model.vals.*
import ui.customComposables.button.customButton
import ui.histogram.maxWidthHistogram

@Composable
fun mainApp(){
    MaterialTheme{
        Column{
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                var path by remember { mutableStateOf("") }
                TextField(
                    value = path,
                    onValueChange = {
                        path = it
                    },
                    modifier = Modifier.width(400.dp),
                    maxLines = 1
                )
                customButton(
                    "Find...",
                    { checkFiles(path) },
                    Modifier
                )
            }
            Column {
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
                var charList = charMap.toList().sortedBy { (_, value) -> value }.reversed()
                var fmpList = fmpMap.toList().sortedBy { (_, value) -> value }.reversed()
                var selfinfoList = selfinfoMap.toList().sortedBy { (_, value) -> value }.reversed()
                LazyColumn {
                    items(charMap.size) {
                        Text("Character ${charList[it].first} is present ${charList[it].second} times," +
                                " will occur %${fmpList[it].second} of the time" +
                                " and has %${selfinfoList[it].second} self information"
                        )
                    }
                }
            }
            Column{
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
                        println(canvasHeight)
                        println(canvasWidth)

                        var counter = 0
                        var canvasHeightToHeight = canvasHeight/100

                        fmpMap.forEach{
                            drawRect(
                                color = Color.Blue,
                                topLeft = Offset((canvasWidth / maxWidthHistogram())*counter,canvasHeight-it.value*canvasHeightToHeight),
                                size = Size(canvasWidth / maxWidthHistogram(), it.value * canvasHeightToHeight)
                            )
                            counter += 1
                        }
                    }
                }
            }
        }
    }
}