package com.example.pokemon.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemon.presentation.common.PokemonViewmodel
import com.example.pokemon.presentation.components.BottomBar
import com.example.pokemon.presentation.components.BottomSheet

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainPage(
    navigateToHome: () -> Unit,
    onCard: (name: String) -> Unit,
    pokemonViewmodel: PokemonViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val errorMesage = pokemonViewmodel.errorMessage.value

    BottomSheet(
        content = {
            Scaffold(
                snackbarHost = {
                    LaunchedEffect(Unit) {
                        snackbarHostState.showSnackbar(
                            message = errorMesage,
                             withDismissAction = false,
                             duration = SnackbarDuration.Long
                     )
                 }
             },
            containerColor = Color.Transparent,
            bottomBar = {
                BottomBar(
                     navigateToHome = { navigateToHome() }
                )
             }
         ) { padding ->
             HomeScreen(
                 paddingValue = padding,
                 onCard = { onCard(it) }
             )
         }
     })
}

@Preview
@Composable
private fun MainPagePreview() {
    MainPage({}, {})
}