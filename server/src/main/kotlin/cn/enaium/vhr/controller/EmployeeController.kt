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

import cn.enaium.vhr.model.entity.*
import cn.enaium.vhr.model.entity.input.EmployeeInput
import cn.enaium.vhr.model.result.Result
import cn.enaium.vhr.repository.*
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/employee")
class EmployeeController(
    val employeeRepository: EmployeeRepository,
    val nationRepository: NationRepository,
    val politicRepository: PoliticRepository,
    val joblevelRepository: JoblevelRepository,
    val positionRepository: PositionRepository,
    val departmentRepository: DepartmentRepository
) {
    @GetMapping
    fun get(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam employee: Employee?,
        @RequestParam beginDateScope: Array<Long>?
    ): Result<Page<Employee>?> {
        return Result.Builder.success(
            metadata = employeeRepository.findAllByEmployee(
                PageRequest.of(page, size),
                employee,
                beginDateScope
            )
        )
    }

    @GetMapping("/nation")
    fun nation(): Result<List<Nation>?> {
        return Result.Builder.success(metadata = nationRepository.findAll())
    }

    @GetMapping("/politic")
    fun politic(): Result<List<Politic>?> {
        return Result.Builder.success(metadata = politicRepository.findAll())
    }

    @GetMapping("/jobLevel")
    fun joblevel(): Result<List<JobLevel>?> {
        return Result.Builder.success(metadata = joblevelRepository.findAll())
    }

    @GetMapping("/position")
    fun positions(): Result<List<Position>?> {
        return Result.Builder.success(metadata = positionRepository.findAll())
    }

    @GetMapping("/department")
    fun department(): Result<List<Department>?> {
        return Result.Builder.success(metadata = departmentRepository.findAll())
    }

    @PutMapping
    fun put(@RequestBody employeeInput: EmployeeInput): Result<Nothing?> {
        employeeRepository.save(employeeInput) {
            setMode(SaveMode.UPDATE_ONLY)
        }
        return Result.Builder.success()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Result<Nothing?> {
        employeeRepository.deleteById(id)
        return Result.Builder.success()
    }
}