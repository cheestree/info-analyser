package ui.histogram

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.vals.canvasHeight
import model.vals.canvasWidth
import model.vals.fmpMap
import model.vals.selfinfoMap
import java.math.RoundingMode

@Composable
fun histogram(){
    Canvas(
            modifier = Modifier
                .padding(4.dp)
                .border(4.dp, Color.Gray)
                .background(Color.Red)
            ){
        canvasWidth = maxWidthHistogram()
        canvasHeight = maxHeightHistogram()
        println(canvasHeight)
        println(canvasWidth)

    }
}

fun maxHeightHistogram() : Float {
    var maxVal = fmpMap.maxOfOrNull { it.value }
    return if(maxVal != null){
        ((maxVal / 10).toBigDecimal().setScale(1, RoundingMode.HALF_UP).toFloat())*10 + 1
    }else{
        0f
    }
}

fun maxWidthHistogram() : Float {
    return selfinfoMap.values.size.toFloat()
}