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

package cn.enaium.vhr.controller.salary

import cn.enaium.vhr.model.entity.Salary
import cn.enaium.vhr.model.entity.input.SalaryInput
import cn.enaium.vhr.model.result.Result
import cn.enaium.vhr.repository.SalaryRepository
import org.springframework.web.bind.annotation.*

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/salary")
class SalaryController(
    val salaryRepository: SalaryRepository
) {
    @GetMapping
    fun get(): Result<List<Salary>?> {
        return Result.Builder.success(metadata = salaryRepository.findAll())
    }

    @PutMapping
    fun put(@RequestBody salary: SalaryInput): Result<Nothing?> {
        salaryRepository.save(salary)
        return Result.Builder.success()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Result<Nothing?> {
        salaryRepository.deleteById(id)
        return Result.Builder.success()
    }
}