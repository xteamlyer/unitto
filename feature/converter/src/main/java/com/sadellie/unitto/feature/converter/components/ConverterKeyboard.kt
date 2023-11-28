/*
 * Unitto is a unit converter for Android
 * Copyright (c) 2022-2023 Elshan Agaev
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

package com.sadellie.unitto.feature.converter.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import com.sadellie.unitto.core.ui.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sadellie.unitto.core.base.Token
import com.sadellie.unitto.core.ui.LocalWindowSize
import com.sadellie.unitto.core.ui.common.KeyboardButtonContentHeightShort
import com.sadellie.unitto.core.ui.common.KeyboardButtonContentHeightTall
import com.sadellie.unitto.core.ui.common.KeyboardButtonFilled
import com.sadellie.unitto.core.ui.common.KeyboardButtonLight
import com.sadellie.unitto.core.ui.common.KeyboardButtonTertiary
import com.sadellie.unitto.core.ui.common.KeypadFlow
import com.sadellie.unitto.core.ui.common.key.UnittoIcons
import com.sadellie.unitto.core.ui.common.key.unittoicons.Backspace
import com.sadellie.unitto.core.ui.common.key.unittoicons.Brackets
import com.sadellie.unitto.core.ui.common.key.unittoicons.Clear
import com.sadellie.unitto.core.ui.common.key.unittoicons.Comma
import com.sadellie.unitto.core.ui.common.key.unittoicons.Divide
import com.sadellie.unitto.core.ui.common.key.unittoicons.Dot
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key0
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key1
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key2
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key3
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key4
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key5
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key6
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key7
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key8
import com.sadellie.unitto.core.ui.common.key.unittoicons.Key9
import com.sadellie.unitto.core.ui.common.key.unittoicons.KeyA
import com.sadellie.unitto.core.ui.common.key.unittoicons.KeyB
import com.sadellie.unitto.core.ui.common.key.unittoicons.KeyC
import com.sadellie.unitto.core.ui.common.key.unittoicons.KeyD
import com.sadellie.unitto.core.ui.common.key.unittoicons.KeyE
import com.sadellie.unitto.core.ui.common.key.unittoicons.KeyF
import com.sadellie.unitto.core.ui.common.key.unittoicons.LeftBracket
import com.sadellie.unitto.core.ui.common.key.unittoicons.Minus
import com.sadellie.unitto.core.ui.common.key.unittoicons.Multiply
import com.sadellie.unitto.core.ui.common.key.unittoicons.Plus
import com.sadellie.unitto.core.ui.common.key.unittoicons.Power
import com.sadellie.unitto.core.ui.common.key.unittoicons.RightBracket
import com.sadellie.unitto.core.ui.common.key.unittoicons.Root
import com.sadellie.unitto.core.ui.common.textfield.FormatterSymbols

@Composable
internal fun DefaultKeyboard(
    modifier: Modifier,
    addDigit: (String) -> Unit,
    clearInput: () -> Unit,
    deleteDigit: () -> Unit,
    allowVibration: Boolean,
    fractional: String,
    middleZero: Boolean,
    acButton: Boolean,
    addBracket: () -> Unit,
) {
    val fractionalIcon = remember { if (fractional == Token.Digit.dot) UnittoIcons.Dot else UnittoIcons.Comma }
    val contentHeight: Float = if (LocalWindowSize.current.heightSizeClass < WindowHeightSizeClass.Medium) KeyboardButtonContentHeightShort else KeyboardButtonContentHeightTall

    KeypadFlow(
        modifier = modifier,
        rows = 5,
        columns = 4
    ) { width, height ->
        val bModifier = Modifier.fillMaxWidth(width).fillMaxHeight(height)

        if (acButton) {
            KeyboardButtonTertiary(bModifier, UnittoIcons.Clear, allowVibration, contentHeight) { clearInput() }
            KeyboardButtonFilled(bModifier, UnittoIcons.Brackets, allowVibration, contentHeight) { addBracket() }
        } else {
            KeyboardButtonFilled(bModifier, UnittoIcons.LeftBracket, allowVibration, contentHeight) { addDigit(Token.Operator.leftBracket) }
            KeyboardButtonFilled(bModifier, UnittoIcons.RightBracket, allowVibration, contentHeight) { addDigit(Token.Operator.rightBracket) }
        }
        KeyboardButtonFilled(bModifier, UnittoIcons.Power, allowVibration, contentHeight) { addDigit(Token.Operator.power) }
        KeyboardButtonFilled(bModifier, UnittoIcons.Root, allowVibration, contentHeight) { addDigit(Token.Operator.sqrt) }

        KeyboardButtonLight(bModifier, UnittoIcons.Key7, allowVibration, contentHeight) { addDigit(Token.Digit._7) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key8, allowVibration, contentHeight) { addDigit(Token.Digit._8) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key9, allowVibration, contentHeight) { addDigit(Token.Digit._9) }
        KeyboardButtonFilled(bModifier, UnittoIcons.Divide, allowVibration, contentHeight) { addDigit(Token.Operator.divide) }

        KeyboardButtonLight(bModifier, UnittoIcons.Key4, allowVibration, contentHeight) { addDigit(Token.Digit._4) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key5, allowVibration, contentHeight) { addDigit(Token.Digit._5) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key6, allowVibration, contentHeight) { addDigit(Token.Digit._6) }
        KeyboardButtonFilled(bModifier, UnittoIcons.Multiply, allowVibration, contentHeight) { addDigit(Token.Operator.multiply) }

        KeyboardButtonLight(bModifier, UnittoIcons.Key1, allowVibration, contentHeight) { addDigit(Token.Digit._1) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key2, allowVibration, contentHeight) { addDigit(Token.Digit._2) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key3, allowVibration, contentHeight) { addDigit(Token.Digit._3) }
        KeyboardButtonFilled(bModifier, UnittoIcons.Minus, allowVibration, contentHeight) { addDigit(Token.Operator.minus) }

        if (middleZero) {
            KeyboardButtonLight(bModifier, fractionalIcon, allowVibration, contentHeight) { addDigit(Token.Digit.dot) }
            KeyboardButtonLight(bModifier, UnittoIcons.Key0, allowVibration, contentHeight) { addDigit(Token.Digit._0) }
        } else {
            KeyboardButtonLight(bModifier, UnittoIcons.Key0, allowVibration, contentHeight) { addDigit(Token.Digit._0) }
            KeyboardButtonLight(bModifier, fractionalIcon, allowVibration, contentHeight) { addDigit(Token.Digit.dot) }
        }
        KeyboardButtonLight(bModifier, UnittoIcons.Backspace, allowVibration, contentHeight, onLongClick = clearInput) { deleteDigit() }
        KeyboardButtonFilled(bModifier, UnittoIcons.Plus, allowVibration, contentHeight) { addDigit(Token.Operator.plus) }
    }
}

@Composable
internal fun NumberBaseKeyboard(
    modifier: Modifier,
    addDigit: (String) -> Unit,
    clearInput: () -> Unit,
    deleteDigit: () -> Unit,
    allowVibration: Boolean
) {
    val contentHeight: Float = if (LocalWindowSize.current.heightSizeClass < WindowHeightSizeClass.Medium) KeyboardButtonContentHeightShort else KeyboardButtonContentHeightTall

    KeypadFlow(
        modifier = modifier,
        rows = 6,
        columns = 3
    ) { width, height ->
        val bModifier = Modifier.fillMaxWidth(width).fillMaxHeight(height)

        KeyboardButtonFilled(bModifier, UnittoIcons.KeyA, allowVibration, contentHeight) { addDigit(Token.Letter._A) }
        KeyboardButtonFilled(bModifier, UnittoIcons.KeyB, allowVibration, contentHeight) { addDigit(Token.Letter._B) }
        KeyboardButtonFilled(bModifier, UnittoIcons.KeyC, allowVibration, contentHeight) { addDigit(Token.Letter._C) }

        KeyboardButtonFilled(bModifier, UnittoIcons.KeyD, allowVibration, contentHeight) { addDigit(Token.Letter._D) }
        KeyboardButtonFilled(bModifier, UnittoIcons.KeyE, allowVibration, contentHeight) { addDigit(Token.Letter._E) }
        KeyboardButtonFilled(bModifier, UnittoIcons.KeyF, allowVibration, contentHeight) { addDigit(Token.Letter._F) }

        KeyboardButtonLight(bModifier, UnittoIcons.Key7, allowVibration, contentHeight) { addDigit(Token.Digit._7) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key8, allowVibration, contentHeight) { addDigit(Token.Digit._8) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key9, allowVibration, contentHeight) { addDigit(Token.Digit._9) }

        KeyboardButtonLight(bModifier, UnittoIcons.Key4, allowVibration, contentHeight) { addDigit(Token.Digit._4) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key5, allowVibration, contentHeight) { addDigit(Token.Digit._5) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key6, allowVibration, contentHeight) { addDigit(Token.Digit._6) }

        KeyboardButtonLight(bModifier, UnittoIcons.Key1, allowVibration, contentHeight) { addDigit(Token.Digit._1) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key2, allowVibration, contentHeight) { addDigit(Token.Digit._2) }
        KeyboardButtonLight(bModifier, UnittoIcons.Key3, allowVibration, contentHeight) { addDigit(Token.Digit._3) }

        // TODO Should be a separate o use custom widthFillFactors and heightFillFactors
        KeyboardButtonLight(bModifier, UnittoIcons.Key0, allowVibration, contentHeight) { addDigit(Token.Digit._0) }
        KeyboardButtonLight(Modifier.fillMaxHeight(height).fillMaxWidth(width * 2), UnittoIcons.Backspace, allowVibration, contentHeight, clearInput) { deleteDigit() }
    }
}

@Preview
@Composable
private fun PreviewConverterKeyboard() {
    DefaultKeyboard(
        modifier = Modifier.fillMaxSize(),
        addDigit = {},
        clearInput = {},
        deleteDigit = {},
        allowVibration = false,
        fractional = FormatterSymbols.Spaces.fractional,
        middleZero = false,
        acButton = true,
        addBracket = {}
    )
}

@Preview
@Composable
private fun PreviewConverterKeyboardNumberBase() {
    NumberBaseKeyboard(
        modifier = Modifier.fillMaxSize(),
        addDigit = {},
        clearInput = {},
        deleteDigit = {},
        allowVibration = false,
    )
}
