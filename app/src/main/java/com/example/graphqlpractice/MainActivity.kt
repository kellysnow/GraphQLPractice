package com.example.graphqlpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.graphqlpractice.presentation.list.LaunchList
import com.example.graphqlpractice.ui.theme.GraphQLPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GraphQLPracticeTheme  {
                Scaffold(topBar = { TopAppBar({ Text(stringResource(R.string.app_name)) }) }) { paddingValues ->
                    Box(Modifier.padding(paddingValues)) {
                        MainNavHost()
                    }
                }
            }
        }
    }


}
@Composable
private fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationDestinations.LAUNCH_LIST) {
        composable(route = NavigationDestinations.LAUNCH_LIST) {
            LaunchList(
                onLaunchClick = { launchId ->
                    navController.navigate("${NavigationDestinations.LAUNCH_DETAILS}/$launchId")
                }
            )
        }

//        composable(route = "${NavigationDestinations.LAUNCH_DETAILS}/{${NavigationArguments.LAUNCH_ID}}") { navBackStackEntry ->
//            LaunchDetails(launchId = navBackStackEntry.arguments!!.getString(NavigationArguments.LAUNCH_ID)!!)
//        }

        composable(route = NavigationDestinations.LOGIN) {
//            Login()
        }
    }
}