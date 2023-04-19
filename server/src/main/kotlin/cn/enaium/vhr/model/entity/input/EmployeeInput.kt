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

package cn.enaium.vhr.model.entity.input;

import cn.enaium.vhr.model.entity.Employee
import cn.enaium.vhr.model.type.Degree
import cn.enaium.vhr.model.type.State
import cn.enaium.vhr.model.type.Wedlock
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers
import java.util.*

data class EmployeeInput(
    /**
     * 员工编号
     */
    val id: Int?,

    /**
     * 员工姓名
     */
    val name: String?,

    /**
     * 性别
     */
    val gender: String?,

    /**
     * 出生日期
     */
    val birthday: Date?,

    /**
     * 身份证号
     */
    val idCard: String?,

    /**
     * 婚姻状况
     */
    val wedlock: Wedlock?,

    /**
     * 民族
     */
    val nationId: Int?,

    /**
     * 籍贯
     */
    val nativePlace: String?,

    /**
     * 政治面貌
     */
    val politicId: Int?,

    /**
     * 邮箱
     */
    val email: String?,

    /**
     * 电话号码
     */
    val phone: String?,

    /**
     * 联系地址
     */
    val address: String?,

    /**
     * 所属部门
     */
    val departmentId: Int?,

    /**
     * 职称ID
     */
    val jobLevelId: Int?,

    /**
     * 职位ID
     */
    val posId: Int?,

    /**
     * 聘用形式
     */
    val engageForm: String?,

    /**
     * 最高学历
     */
    val tiptopDegree: Degree?,

    /**
     * 所属专业
     */
    val specialty: String?,

    /**
     * 毕业院校
     */
    val school: String?,

    /**
     * 入职日期
     */
    val beginDate: Date?,

    /**
     * 在职状态
     */
    val workState: State?,

    /**
     * 合同期限
     */
    val contractTerm: String?,

    /**
     * 转正日期
     */
    val conversionTime: Date?,

    /**
     * 离职日期
     */
    val notWorkDate: Date?,

    /**
     * 合同起始日期
     */
    val beginContract: Date?,

    /**
     * 合同终止日期
     */
    val endContract: Date?,

    /**
     * 工龄
     */
    val workAge: Int?,

    ) : Input<Employee> {

    override fun toEntity(): Employee {
        return CONVERTER.toEmployee(this);
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toEmployee(input: EmployeeInput): Employee;
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}

