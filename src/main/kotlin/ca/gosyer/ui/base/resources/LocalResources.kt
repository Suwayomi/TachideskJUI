/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package ca.gosyer.ui.base.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import ca.gosyer.data.translation.XmlResourceBundle

val LocalResources: ProvidableCompositionLocal<XmlResourceBundle> =
    compositionLocalOf { throw IllegalStateException("resources have not been not initialized") }

@Composable
fun stringResource(key: String): String {
    val resources = LocalResources.current
    return remember(key) { resources.getStringA(key) }
}

@Composable
fun stringResource(key: String, vararg replacements: String): String {
    val resources = LocalResources.current
    return remember(key, replacements) { resources.getString(key, *replacements) }
}
