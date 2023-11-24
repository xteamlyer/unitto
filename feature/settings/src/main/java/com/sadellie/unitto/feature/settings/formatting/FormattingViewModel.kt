/*
 * Unitto is a unit converter for Android
 * Copyright (c) 2023 Elshan Agaev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sadellie.unitto.feature.settings.formatting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadellie.unitto.core.base.MAX_PRECISION
import com.sadellie.unitto.core.ui.common.textfield.AllFormatterSymbols
import com.sadellie.unitto.data.common.stateIn
import com.sadellie.unitto.data.model.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormattingViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _prefs = userPreferencesRepository.formattingPrefs

    val uiState = _prefs.map { mainPrefs ->
        FormattingUIState(
            precision = mainPrefs.digitsPrecision,
            separator = mainPrefs.separator,
            outputFormat = mainPrefs.outputFormat,
            formatterSymbols = AllFormatterSymbols.getById(mainPrefs.separator)
        )
    }
        .stateIn(viewModelScope, null)

    /**
     * @see UserPreferencesRepository.updateDigitsPrecision
     */
    fun updatePrecision(precision: Int) = viewModelScope.launch {
        // In UI the slider for precision goes from 0 to 16, where 16 is treated as 1000 (MAX)
        val newPrecision = if (precision > 15) MAX_PRECISION else precision
        userPreferencesRepository.updateDigitsPrecision(newPrecision)
    }

    /**
     * @see UserPreferencesRepository.updateSeparator
     */
    fun updateSeparator(separator: Int) = viewModelScope.launch {
        userPreferencesRepository.updateSeparator(separator)
    }

    /**
     * @see UserPreferencesRepository.updateOutputFormat
     */
    fun updateOutputFormat(outputFormat: Int) = viewModelScope.launch {
        userPreferencesRepository.updateOutputFormat(outputFormat)
    }
}
