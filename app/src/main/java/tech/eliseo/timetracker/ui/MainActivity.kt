/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.eliseo.timetracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.eliseo.timetracker.ui.screen.analytics.AnalyticsScreen
import tech.eliseo.timetracker.ui.screen.categorylist.CategoryListScreen
import tech.eliseo.timetracker.ui.screen.main.MainScreen
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable(route = "main") {
                            MainScreen(navController = navController, viewModel = hiltViewModel())
                        }

                        composable("category_list") {
                            CategoryListScreen(
                                navController = navController,
                                viewModel = hiltViewModel(it)
                            )
                        }

                        composable("analytics") {
                            AnalyticsScreen(
                                navController = navController,
                                viewModel = hiltViewModel(it)
                            )
                        }

                        /*dialog(
                        route = ProductDetailNavigation.route,
                        arguments = ProductDetailNavigation.arguments,
                    ) {
                        ProductDialogView(
                            hiltViewModel(it)
                        )
                    }*/


                    }
                }
            }
        }
    }
}
