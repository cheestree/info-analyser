package ui.file

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.theory.convertToHistogram
import model.vals.charMap
import model.vals.fileList

@Composable
fun fileApp(){
    MaterialTheme {
        BoxWithConstraints(
            modifier = Modifier.width(250.dp)
        ){
            LazyColumn {
                items(fileList) { file ->
                    Row(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth()
                    ) {
                        Button(
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                charMap = convertToHistogram(file)
                            }
                        ) {
                            Text(
                                file.name,
                                modifier = Modifier,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}