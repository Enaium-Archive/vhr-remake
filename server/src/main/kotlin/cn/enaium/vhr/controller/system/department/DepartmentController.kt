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

package cn.enaium.vhr.controller.system.department

import cn.enaium.vhr.model.entity.Department
import cn.enaium.vhr.model.entity.fetchBy
import cn.enaium.vhr.model.entity.input.DepartmentInput
import cn.enaium.vhr.model.entity.parent
import cn.enaium.vhr.model.result.Result
import cn.enaium.vhr.repository.DepartmentRepository
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.table.isNull
import org.springframework.web.bind.annotation.*

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/system/department")
class DepartmentController(
    val departmentRepository: DepartmentRepository,
    val sqlClient: KSqlClient
) {
    @GetMapping
    fun get(): Result<List<Department>?> {
        return Result.Builder.success(metadata = departmentRepository.findAll())
    }

    @GetMapping("/tree")
    fun getTree(): Result<List<Department>?> {
        val execute = sqlClient.createQuery(Department::class) {
            where(table.parent.isNull())
            select(table.fetchBy {
                allScalarFields()
                children({
                    recursive()
                }) {
                    allScalarFields()
                    parent {
                        allScalarFields()
                    }
                }
            })
        }.execute()
        return Result.Builder.success(metadata = execute)
    }

    @PutMapping
    fun put(@RequestBody department: DepartmentInput): Result<Int?> {
        return Result.Builder.success(metadata = departmentRepository.save(department).id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Result<Nothing?> {
        departmentRepository.deleteById(id)
        return Result.Builder.success()
    }
}