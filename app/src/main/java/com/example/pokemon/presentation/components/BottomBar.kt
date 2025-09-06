package com.example.pokemon.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokemon.R
import com.example.pokemon.ui.theme.lighterGrey

@Composable
fun BottomBar(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.background(Color.Transparent)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 70.dp, end = 70.dp, bottom = 70.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(lighterGrey),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {navigateToHome()}) {
                Icon(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "home icon",
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = "home icon",
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )
            }
        }
    }

}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar({})
}