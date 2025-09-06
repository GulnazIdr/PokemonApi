package com.example.pokemon.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemon.R
import com.example.pokemon.ui.theme.lightGrey
import com.example.pokemon.ui.theme.lighterGrey
import com.example.pokemon.ui.theme.poppins

@Composable
fun SearchBar(
    onValueChanged: (value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = lighterGrey)
                    .weight(1f)
                    .height(38.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(17.dp))

                    BasicTextField(
                        value = input,
                        onValueChange = {
                            input = it
                            onValueChanged(it)
                        },
                        textStyle = TextStyle(
                            fontFamily = poppins,
                            fontSize = 14.sp,
                            color = lightGrey
                        )
                    )
                    if (input.isEmpty()) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = "search icon",
                            modifier = Modifier.size(20.dp),
                            tint = lightGrey
                        )

                        Spacer(modifier = Modifier.width(7.dp))

                        Text(
                            text = "Search",
                            fontFamily = poppins,
                            fontSize = 14.sp,
                            color = lightGrey
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.width(11.dp))

            Icon(
                painter = painterResource(R.drawable.sort),
                contentDescription = "search icon",
                modifier = Modifier.size(25.dp),
                tint = lightGrey
            )
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(
        onValueChanged = {}
    )
}