package ui.customComposables.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun customTextfield(s : String, modifier : Modifier, textFieldFn : (String) -> Unit, varMState : MutableState<String>){
    var currName by remember { varMState }
    Text(
        text = s,
        modifier = modifier
    )
    TextField(
        value = currName,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        onValueChange = {
            textFieldFn(currName)
            currName = it
        }
    )
}