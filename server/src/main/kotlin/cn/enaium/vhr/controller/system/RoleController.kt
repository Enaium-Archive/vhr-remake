/*
 * Copyright 2023 Enaium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.enaium.vhr.controller.system

import cn.enaium.vhr.model.entity.Role
import cn.enaium.vhr.model.entity.by
import cn.enaium.vhr.model.entity.input.RoleInput
import cn.enaium.vhr.model.result.ResponseResult
import cn.enaium.vhr.repository.RoleRepository
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.web.bind.annotation.*

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/system/role")
class RoleController(
    val roleRepository: RoleRepository,
    val sql: KSqlClient
) {
    @GetMapping
    fun get(): ResponseResult<List<Role>?> {
        return ResponseResult.Builder.success(metadata = roleRepository.findAll())
    }

    @PutMapping
    fun put(@RequestBody roleInput: RoleInput): ResponseResult<Nothing?> {
        if (!roleInput.name.startsWith("ROLE_")) {
            roleInput.name = "ROLE_${roleInput.name}"
        }
        roleRepository.save(roleInput) {
            setMode(SaveMode.INSERT_ONLY)
        }
        return ResponseResult.Builder.success()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseResult<Nothing?> {
        roleRepository.deleteById(id)
        return ResponseResult.Builder.success()
    }

    @GetMapping("/{id}/menuIds")
    fun getMenuIds(@PathVariable id: Int): ResponseResult<List<Int>?> {
        val findNullable = roleRepository.findNullable(id, newFetcher(Role::class).by {
            menus()
        }) ?: return ResponseResult.Builder.fail(status = ResponseResult.Status.ROLE_DOESNT_EXIST)
        return ResponseResult.Builder.success(metadata = findNullable.menus.map { it.id })
    }

    @PutMapping("/{id}/menuIds")
    fun putMenuIds(@PathVariable id: Int, menuIds: Array<Int>): ResponseResult<Nothing?> {
        val findNullable = roleRepository.findNullable(id, newFetcher(Role::class).by {
            menus()
        }) ?: return ResponseResult.Builder.fail(status = ResponseResult.Status.ROLE_DOESNT_EXIST)
        sql.getAssociations(Role::menus).batchDelete(listOf(id), findNullable.menus.map { it.id })
        sql.getAssociations(Role::menus).batchSave(listOf(id), menuIds.toList())
        return ResponseResult.Builder.success()
    }
}