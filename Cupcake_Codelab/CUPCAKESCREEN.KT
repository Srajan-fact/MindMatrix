

package com.example.cupcake

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.OrderViewModel
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen

// ✅ ROUTES ENUM
enum class CupcakeScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}

// ✅ APP BAR
@Composable
fun CupcakeAppBar(
    currentScreen: CupcakeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

// ✅ MAIN APP
@Composable
fun CupcakeApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = CupcakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
    )

    Scaffold(
        topBar = {
            CupcakeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CupcakeScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // 🔹 START SCREEN
            composable(CupcakeScreen.Start.name) {
                StartOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = { quantity ->
                        viewModel.setQuantity(quantity)
                        navController.navigate(CupcakeScreen.Flavor.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            // 🔹 FLAVOR SCREEN
            composable(CupcakeScreen.Flavor.name) {
                val context = LocalContext.current

                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = DataSource.flavors.map { id ->
                        context.resources.getString(id)
                    },
                    onSelectionChanged = { viewModel.setFlavor(it) },

                    onNextButtonClicked = {
                        navController.navigate(CupcakeScreen.Pickup.name)
                    },

                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(
                            CupcakeScreen.Start.name,
                            false
                        )
                    },

                    modifier = Modifier.fillMaxSize()
                )
            }

            // 🔹 PICKUP SCREEN
            composable(CupcakeScreen.Pickup.name) {
                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = uiState.pickupOptions,

                    onSelectionChanged = { viewModel.setDate(it) },

                    onNextButtonClicked = {
                        navController.navigate(CupcakeScreen.Summary.name)
                    },

                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(
                            CupcakeScreen.Start.name,
                            false
                        )
                    },

                    modifier = Modifier.fillMaxSize()
                )
            }

            // 🔹 SUMMARY SCREEN
            composable(CupcakeScreen.Summary.name) {
                val context = LocalContext.current

                OrderSummaryScreen(
                    orderUiState = uiState,

                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(
                            CupcakeScreen.Start.name,
                            false
                        )
                    },

                    onSendButtonClicked = { subject, summary ->
                        shareOrder(context, subject, summary)
                    },

                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

// ✅ SHARE FUNCTION
private fun shareOrder(
    context: Context,
    subject: String,
    summary: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )
}