package com.example.pokemon.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemon.R
import com.example.pokemon.data.pokemon.Pokemon
import com.example.pokemon.ui.theme.lightGrey
import com.example.pokemon.ui.theme.poppins

@Composable
fun PokemonDetails(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pokemonInfo = Pokemon(
        image = "",
        name = "Pikachu",
        id = 0
    )

    Scaffold(modifier = modifier) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Box(
                modifier = modifier.fillMaxWidth(),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "image inside of the button",
                    modifier = Modifier
                        .size(44.dp)
                        .clickable(onClick = {  })
                        .align(Alignment.TopStart),
                    tint = Color.Unspecified
                )

                Text(
                    text = pokemonInfo.name,
                    fontFamily = poppins,
                    fontSize = 24.sp,
                    color = lightGrey,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Icon(
                painter = painterResource(R.drawable.pikachu),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
private fun PokemonCardPreview() {
    PokemonDetails(
        onBack = {}
    )
}