package com.example.pokemon.presentation.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.pokemon.data.pokemon.Pokemon
import com.example.pokemon.ui.theme.poppins

@Composable
fun PokemonCard(
    onCard: (name: String) -> Unit,
    pokemonInfo: Pokemon,
    modifier: Modifier = Modifier
) {
    var backgroundColor by remember { mutableStateOf(Color.White) }
    Box(modifier = modifier
        .clip(RoundedCornerShape(20.dp))
        .width(154.dp)
        .height(185.dp)
        .background(color = backgroundColor)
        .clickable(onClick = {onCard(pokemonInfo.name)})
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemonInfo.image)
                .diskCacheKey(pokemonInfo.id.toString())
                .build(),
            contentDescription = "pokemon image",
            modifier = Modifier
                .width(150.dp)
                .height(160.dp)
                .align(Alignment.Center),
            loading = {
               Box{CircleLoading()}
            },
            error = {Log.e("IMAGE LOADING", "${it.result.throwable} ${pokemonInfo.image}")},
            onSuccess = { res ->
                val bitmap = (res.result.drawable as BitmapDrawable )
                    .bitmap.copy(Bitmap.Config.ARGB_8888, true)
                Palette.from(bitmap).generate { palette ->
                    if (palette != null)
                        backgroundColor = Color(palette.dominantSwatch!!.rgb)
                }
            }
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = pokemonInfo.name,
            fontFamily = poppins,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 15.dp, bottom = 9.dp)
                .align(Alignment.BottomStart)
        )
    }
}

@Preview
@Composable
private fun PokemonCardPreview() {
    PokemonCard(
        onCard = {},
        pokemonInfo = Pokemon(
            image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
            name = "Pikachu",
            id = 0
        )
    )
}