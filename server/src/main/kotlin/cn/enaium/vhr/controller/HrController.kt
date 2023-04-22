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

package cn.enaium.vhr.controller

import cn.dev33.satoken.annotation.SaIgnore
import cn.dev33.satoken.secure.BCrypt
import cn.enaium.vhr.model.entity.Hr
import cn.enaium.vhr.model.entity.Menu
import cn.enaium.vhr.model.entity.by
import cn.enaium.vhr.model.entity.input.HrInput
import cn.enaium.vhr.model.result.ResponseResult
import cn.enaium.vhr.repository.HrRepository
import cn.enaium.vhr.repository.MenuRepository
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.web.bind.annotation.*

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/hr")
class HrController(
    val hrRepository: HrRepository,
    val menuRepository: MenuRepository,
    val sql: KSqlClient
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseResult<Hr?> {
        return ResponseResult.Builder.success(metadata = hrRepository.findNullable(id, newFetcher(Hr::class).by {
            allScalarFields()
            password(false)
            roles {
                allScalarFields()
            }
        }))
    }

    @GetMapping
    fun get(hrInput: HrInput): ResponseResult<List<Hr>?> {
        return ResponseResult.Builder.success(metadata = hrRepository.findByHr(hrInput))
    }

    @PutMapping
    fun save(@RequestBody hrInput: HrInput): ResponseResult<Nothing?> {
        if (hrInput.oldPassword != null) {
            hrInput.id ?: return ResponseResult.Builder.fail(message = "ID不存在")
            val hr = hrRepository.findNullable(hrInput.id)
                ?: return ResponseResult.Builder.fail(status = ResponseResult.Status.HR_DOESNT_EXIST)

            if (!BCrypt.checkpw(hrInput.oldPassword, hr.password)) {
                return ResponseResult.Builder.fail(status = ResponseResult.Status.PASSWORD_INCORRECT)
            } else {
                hrInput.password = BCrypt.hashpw(hrInput.password)
            }
        }

        hrRepository.save(hrInput)
        return ResponseResult.Builder.success()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseResult<Nothing?> {
        hrRepository.deleteById(id)
        return ResponseResult.Builder.success()
    }

    @SaIgnore
    @GetMapping("/{id}/menu")
    fun getMenu(@PathVariable id: Int): ResponseResult<List<Menu>?> {
        return ResponseResult.Builder.success(metadata = menuRepository.findAllByHrId(id))
    }

    @PutMapping("/{id}/roleIds")
    fun putRoleIds(@PathVariable id: Int, roleIds: Array<Int>): ResponseResult<Nothing?> {
        val findNullable = hrRepository.findNullable(id, newFetcher(Hr::class).by {
            roles()
        }) ?: return ResponseResult.Builder.fail(status = ResponseResult.Status.HR_DOESNT_EXIST)
        sql.getAssociations(Hr::roles).batchDelete(listOf(id), findNullable.roles.map { it.id })
        sql.getAssociations(Hr::roles).batchSave(listOf(id), roleIds.toList())
        return ResponseResult.Builder.success()
    }
}