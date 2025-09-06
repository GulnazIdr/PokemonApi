package com.example.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokemon.presentation.MainPage
import com.example.pokemon.presentation.PokemonDetails
import com.example.pokemon.ui.theme.PokemonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            PokemonTheme {
                NavHost(
                    startDestination = Home,
                    navController = navController
                ){
                    composable<Home> {
                        MainPage(
                            navigateToHome = {navController.navigate(Home)},
                            onCard = {navController.navigate(PokemonDetails(it))}
                        )
                    }

                    composable<PokemonDetails> {
                        val args = it.toRoute<PokemonDetails>().name
                        PokemonDetails(
                            onBack = {}
                        )
                    }

                }
            }
        }
    }
}
