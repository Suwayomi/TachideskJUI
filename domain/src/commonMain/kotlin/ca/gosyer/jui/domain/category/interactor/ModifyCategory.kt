/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package ca.gosyer.jui.domain.category.interactor

import ca.gosyer.jui.domain.category.model.Category
import ca.gosyer.jui.domain.category.service.CategoryRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import me.tatarka.inject.annotations.Inject
import org.lighthousegames.logging.logging

class ModifyCategory @Inject constructor(private val categoryRepository: CategoryRepository) {

    suspend fun await(categoryId: Long, name: String? = null, isLanding: Boolean? = null) = asFlow(
        categoryId = categoryId,
        name = name,
        isLanding = isLanding
    ).catch { log.warn(it) { "Failed to modify category $categoryId with options: name=$name,isLanding=$isLanding" } }.collect()

    suspend fun await(category: Category, name: String? = null, isLanding: Boolean? = null) = asFlow(
        category = category,
        name = name,
        isLanding = isLanding
    ).catch { log.warn(it) { "Failed to modify category ${category.name} with options: name=$name,isLanding=$isLanding" } }.collect()

    fun asFlow(categoryId: Long, name: String? = null, isLanding: Boolean? = null) = categoryRepository.modifyCategory(
        categoryId = categoryId,
        name = name,
        isLanding = isLanding
    )

    fun asFlow(category: Category, name: String? = null, isLanding: Boolean? = null) = categoryRepository.modifyCategory(
        categoryId = category.id,
        name = name,
        isLanding = isLanding
    )

    companion object {
        private val log = logging()
    }
}