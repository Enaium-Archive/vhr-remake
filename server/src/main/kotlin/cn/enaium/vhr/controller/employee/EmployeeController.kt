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

package cn.enaium.vhr.controller.employee

import cn.enaium.vhr.excel2Cell
import cn.enaium.vhr.getEnumByOrigin
import cn.enaium.vhr.model.entity.*
import cn.enaium.vhr.model.entity.input.EmployeeInput
import cn.enaium.vhr.model.result.ResponseResult
import cn.enaium.vhr.model.type.Degree
import cn.enaium.vhr.model.type.State
import cn.enaium.vhr.model.type.Wedlock
import cn.enaium.vhr.repository.*
import cn.enaium.vhr.string2Excel
import jakarta.servlet.http.HttpServletResponse
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.babyfish.jimmer.kt.new
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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
        employee: EmployeeInput?,
        beginDateScope: Array<String>?
    ): ResponseResult<Page<Employee>?> {
        return ResponseResult.Builder.success(
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
    fun nation(): ResponseResult<List<Nation>?> {
        return ResponseResult.Builder.success(metadata = nationRepository.findAll())
    }

    @GetMapping("/politic")
    fun politic(): ResponseResult<List<Politic>?> {
        return ResponseResult.Builder.success(metadata = politicRepository.findAll())
    }

    @GetMapping("/jobLevel")
    fun joblevel(): ResponseResult<List<JobLevel>?> {
        return ResponseResult.Builder.success(metadata = jobLevelRepository.findAll())
    }

    @GetMapping("/position")
    fun positions(): ResponseResult<List<Position>?> {
        return ResponseResult.Builder.success(metadata = positionRepository.findAll())
    }

    @GetMapping("/department")
    fun department(): ResponseResult<List<Department>?> {
        return ResponseResult.Builder.success(metadata = departmentRepository.findAll())
    }

    @PutMapping
    fun put(@RequestBody employeeInput: EmployeeInput): ResponseResult<Nothing?> {
        employeeRepository.save(employeeInput)
        return ResponseResult.Builder.success()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseResult<Nothing?> {
        employeeRepository.deleteById(id)
        return ResponseResult.Builder.success()
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
            }
            pos {
                name()
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
                it.nation.name,
                "${it.nativePlace}",
                it.politic.name,
                "${it.phone}",
                "${it.address}",
                it.department.name,
                "${it.jobLevel.name}",
                "${it.pos.name}",
                "${it.engageFrom}",
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

    @Transactional
    @PutMapping("/import")
    fun import(file: MultipartFile): ResponseResult<Nothing?> {
        val nations = nationRepository.findAll().associate { it.name to it.id }
        val politics = politicRepository.findAll().associate { it.name to it.id }
        val departments = departmentRepository.findAll().associate { it.name to it.id }
        val jobLevels = jobLevelRepository.findAll().associate { it.name to it.id }
        val positions = positionRepository.findAll().associate { it.name to it.id }
        println(positions)
        excel2Cell(HSSFWorkbook(file.inputStream), 1, 1).forEach { sheet ->
            sheet?.forEach { row ->
                var index = 0
                employeeRepository.insert(new(Employee::class).by {
                    name = row?.get(index++)?.stringCellValue
                    workId = row?.get(index++)?.stringCellValue
                    gender = row?.get(index++)?.stringCellValue
                    birthday = LocalDate.parse(row?.get(index++)?.stringCellValue)
                    idCard = row?.get(index++)?.stringCellValue
                    wedlock = getEnumByOrigin<Wedlock>(row?.get(index++)?.stringCellValue)
                    nationId = nations[row?.get(index++)?.stringCellValue]!!
                    nativePlace = row?.get(index++)?.stringCellValue
                    politicId = politics[row?.get(index++)?.stringCellValue]!!
                    phone = row?.get(index++)?.stringCellValue
                    address = row?.get(index++)?.stringCellValue
                    departmentId = departments[row?.get(index++)?.stringCellValue]!!
                    jobLevelId = jobLevels[row?.get(index++)?.stringCellValue]!!
                    posId = positions[row?.get(index++)?.stringCellValue]!!
                    engageFrom = row?.get(index++)?.stringCellValue
                    tiptopDegree = getEnumByOrigin<Degree>(row?.get(index++)?.stringCellValue)
                    specialty = row?.get(index++)?.stringCellValue
                    school = row?.get(index++)?.stringCellValue
                    beginDate = LocalDate.parse(row?.get(index++)?.stringCellValue)
                    workState = getEnumByOrigin<State>(row?.get(index++)?.stringCellValue)
                    email = row?.get(index++)?.stringCellValue
                    contractTerm = row?.get(index++)?.stringCellValue?.toDouble()
                    beginContract = LocalDate.parse(row?.get(index++)?.stringCellValue)
                    endContract = LocalDate.parse(row?.get(index++)?.stringCellValue)
                })

            }
        }
        return ResponseResult.Builder.success()
    }
}