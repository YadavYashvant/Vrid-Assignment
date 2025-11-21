package com.yashvant.vrid_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yashvant.vrid_assignment.ui.screens.BlogDetailScreen
import com.yashvant.vrid_assignment.ui.screens.BlogListScreen
import com.yashvant.vrid_assignment.ui.theme.VridAssignmentTheme
import com.yashvant.vrid_assignment.ui.viewmodel.BlogViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VridAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: BlogViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "blog_list"
                    ) {
                        composable("blog_list") {
                            BlogListScreen(
                                viewModel = viewModel,
                                onBlogClick = { blogId, blogUrl ->
                                    val encodedUrl = URLEncoder.encode(
                                        blogUrl,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate("blog_detail/$encodedUrl")
                                }
                            )
                        }

                        composable(
                            route = "blog_detail/{blogUrl}",
                            arguments = listOf(
                                navArgument("blogUrl") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val encodedUrl = backStackEntry.arguments?.getString("blogUrl") ?: ""
                            BlogDetailScreen(
                                blogUrl = encodedUrl,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}