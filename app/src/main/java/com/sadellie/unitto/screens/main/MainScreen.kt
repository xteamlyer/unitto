package com.sadellie.unitto.screens.main


import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sadellie.unitto.R
import com.sadellie.unitto.data.SECOND_SCREEN
import com.sadellie.unitto.data.SETTINGS_SCREEN
import com.sadellie.unitto.data.units.AbstractUnit
import com.sadellie.unitto.screens.MainScreenUIState
import com.sadellie.unitto.screens.MainViewModel
import com.sadellie.unitto.screens.main.components.Keyboard
import com.sadellie.unitto.screens.main.components.TopScreenPart


@Composable
fun MainScreen(
    navControllerAction: (String) -> Unit = {},
    viewModel: MainViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W600)
                    )
                },
                actions = {
                    IconButton(onClick = { navControllerAction(SETTINGS_SCREEN) }) {
                        Icon(
                            Icons.Outlined.MoreVert,
                            contentDescription = stringResource(id = R.string.open_settings_description)
                        )
                    }
                },
                // Makes the background of the top bar transparent, by default uses secondary color
                colors = TopAppBarDefaults
                    .centerAlignedTopAppBarColors(containerColor = Color.Transparent)
            )
        },
        content = { padding ->
            PortraitMainScreenContent(
                modifier = Modifier.padding(padding),
                unitFrom = viewModel.unitFrom,
                unitTo = viewModel.unitTo,
                portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT,
                mainScreenUIState = viewModel.mainUIState,
                navControllerAction = { navControllerAction("$SECOND_SCREEN/${it}") },
                swapMeasurements = { viewModel.swapUnits() },
                processInput = { viewModel.processInput(it) },
                deleteDigit = { viewModel.deleteDigit() },
                clearInput = { viewModel.clearInput() },
                negateInput = { viewModel.negateInput() }
            )
        }
    )
}

@Composable
private fun PortraitMainScreenContent(
    modifier: Modifier,
    unitFrom: AbstractUnit,
    unitTo: AbstractUnit,
    portrait: Boolean = true,
    mainScreenUIState: MainScreenUIState = MainScreenUIState(),
    navControllerAction: (Boolean) -> Unit = {},
    swapMeasurements: () -> Unit = {},
    processInput: (String) -> Unit = {},
    deleteDigit: () -> Unit = {},
    clearInput: () -> Unit = {},
    negateInput: () -> Unit = {},
) {
    if (portrait) {
        Column(
            modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TopScreenPart(
                modifier = Modifier,
                inputValue = mainScreenUIState.inputValue,
                outputValue = mainScreenUIState.resultValue,
                unitFrom = unitFrom,
                unitTo = unitTo,
                loadingDataStore = mainScreenUIState.isLoadingDataStore,
                loadingNetwork = mainScreenUIState.isLoadingNetwork,
                networkError = mainScreenUIState.showError,
                onUnitSelectionClick = navControllerAction,
                swapUnits = swapMeasurements
            )
            // Keyboard which takes half the screen
            Keyboard(
                Modifier
                    .fillMaxSize(),
                addDigit = processInput,
                deleteDigit = deleteDigit,
                clearInput = clearInput,
                negateAction = negateInput,
                dotButtonEnabled = mainScreenUIState.dotButtonEnabled,
                deleteButtonEnabled = mainScreenUIState.deleteButtonEnabled,
                negateButtonEnabled = mainScreenUIState.negateButtonEnabled,
            )
        }
    } else {
        Row(
            modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TopScreenPart(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                inputValue = mainScreenUIState.inputValue,
                outputValue = mainScreenUIState.resultValue,
                unitFrom = unitFrom,
                unitTo = unitTo,
                loadingDataStore = mainScreenUIState.isLoadingDataStore,
                loadingNetwork = mainScreenUIState.isLoadingNetwork,
                networkError = mainScreenUIState.showError,
                onUnitSelectionClick = navControllerAction,
                swapUnits = swapMeasurements
            )

            // Keyboard which takes half the screen
            Keyboard(
                Modifier
                    .weight(1f)
                    .fillMaxSize(),
                addDigit = processInput,
                deleteDigit = deleteDigit,
                clearInput = clearInput,
                negateAction = negateInput,
                dotButtonEnabled = mainScreenUIState.dotButtonEnabled,
                deleteButtonEnabled = mainScreenUIState.deleteButtonEnabled,
                negateButtonEnabled = mainScreenUIState.negateButtonEnabled,
            )
        }
    }
}
