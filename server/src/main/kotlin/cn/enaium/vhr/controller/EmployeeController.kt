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
import cn.enaium.vhr.string2Excel
import jakarta.servlet.http.HttpServletResponse
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
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
    val jobLevelRepository: JobLevelRepository,
    val positionRepository: PositionRepository,
    val departmentRepository: DepartmentRepository
) {
    @GetMapping
    fun get(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        employee: Employee?,
        beginDateScope: Array<String>?
    ): Result<Page<Employee>?> {
        return Result.Builder.success(
            metadata = employeeRepository.findAllByEmployee(
                PageRequest.of(page, size),
                employee,
                beginDateScope?.map {
                    LocalDate.parse(it)
                }?.toTypedArray()
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
        return Result.Builder.success(metadata = jobLevelRepository.findAll())
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
        employeeRepository.save(employeeInput)
        return Result.Builder.success()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Result<Nothing?> {
        employeeRepository.deleteById(id)
        return Result.Builder.success()
    }

    @GetMapping("/export")
    fun export(httpServletResponse: HttpServletResponse) {
        val titles = arrayOf(
            "编号",
            "姓名",
            "工号",
            "性别",
            "出生日期",
            "身份证号码",
            "婚姻状况",
            "民族",
            "籍贯",
            "政治面貌",
            "电话号码",
            "联系地址",
            "所属部门",
            "职称",
            "职位",
            "聘用形式",
            "最高学历",
            "专业",
            "毕业院校",
            "入职日期",
            "在职状态",
            "邮箱",
            "合同期限(年)",
            "合同起始日期",
            "合同终止日期"
        )

        val values = employeeRepository.findAll(newFetcher(Employee::class).by {
            allScalarFields()
            nation { name() }
            politic { name() }
            department { name() }
            jobLevel {
                name()
                titleLevel()
            }
        }).map {
            arrayOf(
                "${it.id}",
                "${it.name}",
                "${it.workId}",
                "${it.gender}",
                "${it.birthday}",
                "${it.idCard}",
                "${it.wedlock?.origin}",
                "${it.nation.name}",
                "${it.nativePlace}",
                "${it.politic.name}",
                "${it.phone}",
                "${it.address}",
                "${it.department.name}",
                "${it.jobLevel.name}",
                "${it.jobLevel.titleLevel?.origin}",
                "${it.engageForm}",
                "${it.tiptopDegree?.origin}",
                "${it.specialty}",
                "${it.school}",
                "${it.beginDate}",
                "${it.workState?.origin}",
                "${it.email}",
                "${it.contractTerm}",
                "${it.beginContract}",
                "${it.endContract}",
            )
        }.toTypedArray()

        val sheetName = "${System.currentTimeMillis()}.xls"
        val string2Excel = string2Excel(sheetName, titles, values)
        httpServletResponse.contentType = "application/vnd.ms-excel;charset=utf-8"
        httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$sheetName")
        val outputStream = httpServletResponse.outputStream
        string2Excel.write(outputStream)
        outputStream.flush()
        outputStream.close()
    }
}