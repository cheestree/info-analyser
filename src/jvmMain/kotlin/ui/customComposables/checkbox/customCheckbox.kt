package ui.customComposables.checkbox

import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun customCheckbox(checked : Boolean, onChecked : () -> Unit, s : String, modifier : Modifier){
    Checkbox(checked = checked, onCheckedChange = { onChecked() } )
    Text(text = s, modifier = modifier)
}