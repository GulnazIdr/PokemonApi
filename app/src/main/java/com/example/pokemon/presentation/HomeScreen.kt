package com.example.pokemon.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemon.presentation.common.PokemonViewmodel
import com.example.pokemon.presentation.components.BottomBar
import com.example.pokemon.presentation.components.PokemonCard
import com.example.pokemon.presentation.components.SearchBar
import com.example.pokemon.ui.theme.poppins
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onCard: (name: String) -> Unit,
    pokemonViewmodel: PokemonViewmodel = hiltViewModel(),
    paddingValue: PaddingValues
) {
    val pokemonList = pokemonViewmodel.pokemonList
    val remoteSearchedPokemonList = pokemonViewmodel.remoteSearchedPokemonList
    val isLoading = pokemonViewmodel.isLoading.value
    val isPageEnded = pokemonViewmodel.isPageEnded.value
    val isSearching = pokemonViewmodel.isSearching
    val isCachingData = pokemonViewmodel.isCachingData.value

    var currentList =
        if (isSearching) remoteSearchedPokemonList
        else pokemonList

    val listState = rememberLazyGridState()
    val originalScrollState = remember { mutableIntStateOf(0) }

    LaunchedEffect(isSearching) {
        if (isSearching) {
            originalScrollState.value = listState.firstVisibleItemIndex
        }
    }

    LaunchedEffect(!isSearching) {
        if (!isSearching) {
            delay(100)
            listState.scrollToItem(originalScrollState.value)
        }
    }

    Box(modifier = modifier
        .background(Color.Black)
        .padding(top = paddingValue.calculateTopPadding())
        .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "What Pokemon are you looking for?",
                fontFamily = poppins,
                lineHeight = 40.sp,
                fontSize = 34.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(18.dp))

            SearchBar(
                onValueChanged = {pokemonViewmodel.searchPokemon(it)}
            )

            Spacer(modifier = Modifier.height(41.dp))

            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalArrangement = Arrangement.spacedBy(38.dp)
            ) {

                items(
                    currentList.size
                ) {
                    if (
                        it >= currentList.size- 1 &&
                        !isPageEnded &&
                        !isLoading &&
                        !isSearching &&
                        !isCachingData
                    ){
                        LaunchedEffect(Unit) {
                            pokemonViewmodel.fetchPokemonList()
                        }
                    }

                    PokemonCard(
                        pokemonInfo = currentList[it],
                        onCard = { onCard(it) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(paddingValue = PaddingValues(10.dp), onCard = {})
}