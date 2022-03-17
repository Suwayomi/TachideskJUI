/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package ca.gosyer.ui.main.more.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import ca.gosyer.i18n.MR
import ca.gosyer.ui.base.navigation.Toolbar
import ca.gosyer.ui.main.MoreMenus
import ca.gosyer.uicore.resources.stringResource
import ca.gosyer.uicore.resources.toPainter
import cafe.adriel.voyager.navigator.LocalNavigator

@Composable
fun MoreContent() {
    Scaffold(
        topBar = {
            Toolbar(stringResource(MR.strings.location_more))
        }
    ) {
        LazyColumn(Modifier.padding(it)) {
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = MR.images.icon.toPainter(),
                        contentDescription = "icon",
                        modifier = Modifier.height(140.dp).padding(vertical = 8.dp)
                    )
                }
            }
            item {
                Divider()
            }
            MoreMenus.values().asList().fastForEach {
                item {
                    val navigator = LocalNavigator.current
                    Row(
                        Modifier
                            .height(48.dp)
                            .fillMaxWidth()
                            .clickable {
                                navigator ?: return@clickable
                                navigator push it.createScreen()
                            }
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = it.unselectedIcon,
                            contentDescription = stringResource(it.textKey),
                            modifier = Modifier.size(26.dp),
                            tint = MaterialTheme.colors.onSurface
                        )
                        Spacer(Modifier.width(24.dp))
                        Column {
                            Text(stringResource(it.textKey), color = MaterialTheme.colors.onSurface)
                            if (it.extraInfo != null) {
                                it.extraInfo?.invoke()
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
@Preview
@Composable
private fun MorePreview() {
    MoreContent()
}
*/