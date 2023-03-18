package ui.customComposables.dropdownmenu

import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.customComposables.button.customButton

@Composable
fun customDropDownMenu(
    s: String,
    function: () -> Unit,
    buttonMod: Modifier,
    isExpanded: Boolean,
    function1: () -> Unit,
    function2: @Composable () -> Unit
) {
    Box {
        customButton(
            s,
            { function() },
            buttonMod
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                function1()
            }
        ) {
            function2()
        }
    }
}