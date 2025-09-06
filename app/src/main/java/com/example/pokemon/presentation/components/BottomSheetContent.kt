package com.example.pokemon.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemon.ui.theme.Pink40
import com.example.pokemon.ui.theme.brown
import com.example.pokemon.ui.theme.darkBlue
import com.example.pokemon.ui.theme.fairy
import com.example.pokemon.ui.theme.lightblue
import com.example.pokemon.ui.theme.lighterGrey
import com.example.pokemon.ui.theme.nature
import com.example.pokemon.ui.theme.poppins
import com.example.pokemon.ui.theme.red

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier
) {
    val filterTexts = listOf("All", "Fire", "Water", "Electric", "Grass", "Poison",
        "Flying", "Bug", "Normal", "Fighting", "Ice", "Ground", "Rock", "Ghost",
        "Dragon", "Dark","Fairy" )

    val backgroundColors = listOf(Color.White, Color.Red, lightblue, Color.Blue,
        Color.Green, Color.Yellow, Color.Gray, nature, Color.White, Pink40,
        darkBlue, brown, Color.Black, Color.Transparent, red, lighterGrey, fairy
    )

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Filter by",
            fontSize = 34.sp,
            color = Color.White,
            fontFamily = poppins
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(105.dp)
        ) {
            items(17) {
                FilterBox(
                    filterText = filterTexts[it],
                    backgroundColor = backgroundColors[it]
                )
            }
        }
    }
}

@Composable
fun FilterBox(
    filterText: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    var focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var isFocused = interactionSource.collectIsFocusedAsState().value

    val colors = ButtonDefaults.buttonColors().copy(
        contentColor = Color.Black,
        containerColor = if(isFocused) backgroundColor else Color.White
    )

    Button(
        onClick = {focusRequester.requestFocus()},
        shape = RoundedCornerShape(13.dp),
        modifier = modifier
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
            .padding(5.dp),
        colors = colors,
    ) {
        Text(
            text = filterText,
            fontSize = 14.sp,
            color = Color.Black,
            fontFamily = poppins,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun BottomSheetContentPreview() {
    BottomSheetContent()
}