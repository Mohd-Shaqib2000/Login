package com.example.login

import android.R.attr.password
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = "register"
    ) {
        // Register Screen
        composable("register") {
            RegisterScreen(                                      // function call
                onRegisterSuccess = {                            //pass argument -> That’s a lambda (callback) function return
                                                                //basically, a way for the screen to “notify” navigation when registration succeeds.

                    navController.navigate("login")         //This calls the lambda that was passed here and navigates to the login screen.
                }
            )
        }

        // Login Screen
        composable("login") {
            LoginScreen(
                onLoginSuccess = { email, password ->
//                    navController.navigate("welcome/$email")
                           // OR
                    navController.navigate("details/$email/$password")
                }
            )
        }

//        // Welcome Screen
//        composable("welcome/{email}") { backStackEntry ->
//            val email = backStackEntry.arguments?.getString("email") ?: ""
//            WelcomeScreen(email)                                                     //function call
//        }



        // Details Screen
        composable("details/{email}/{password}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            DetailsScreen(email, password)
        }


    }
}
