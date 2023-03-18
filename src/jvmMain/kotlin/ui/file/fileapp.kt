package ui.file

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.theory.convertToHistogram
import model.theory.fmp
import model.theory.selfinfo
import model.vals.charMap
import model.vals.fileList
import model.vals.fmpMap
import model.vals.selfinfoMap

@Composable
fun fileApp(){
    MaterialTheme {
        LazyColumn {
            println(fileList)
            items(fileList) { file ->
                Row(
                    modifier = Modifier
                        .padding(2.dp)
                        //.width(30.dp)
                ){
                    Button(
                        onClick = {
                            //println(file)
                            charMap = convertToHistogram(file)
                            fmpMap = fmp(charMap)
                            selfinfoMap = selfinfo(fmpMap)
                        }
                    ){
                        Text(
                            file.name,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}