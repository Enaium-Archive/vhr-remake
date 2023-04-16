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

import cn.dev33.satoken.secure.BCrypt
import cn.enaium.vhr.model.entity.Hr
import cn.enaium.vhr.model.entity.by
import cn.enaium.vhr.model.entity.input.HrInput
import cn.enaium.vhr.model.result.Result
import cn.enaium.vhr.repository.HrRepository
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.web.bind.annotation.*

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/hr")
class HrController(val hrRepository: HrRepository) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): Result<Hr?> {
        return Result.Builder.success(metadata = hrRepository.findNullable(id, newFetcher(Hr::class).by {
            allScalarFields()
            roles {
                allScalarFields()
            }
        }))
    }

    @PutMapping
    fun save(@RequestBody hrInput: HrInput): Result<Nothing?> {
        if (hrInput.oldPassword != null) {
            hrInput.id ?: return Result.Builder.fail(message = "ID不存在")
            val hr = hrRepository.findNullable(hrInput.id)
                ?: return Result.Builder.fail(status = Result.Status.HR_DOESNT_EXIST)

            if (!BCrypt.checkpw(hrInput.oldPassword, hr.password)) {
                return Result.Builder.fail(status = Result.Status.PASSWORD_INCORRECT)
            } else {
                hrInput.password = BCrypt.hashpw(hrInput.password)
            }
        }

        hrRepository.save(hrInput)
        return Result.Builder.success()
    }
}