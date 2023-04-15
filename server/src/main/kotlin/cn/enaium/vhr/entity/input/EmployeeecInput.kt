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

package cn.enaium.vhr.entity.input

import cn.enaium.vhr.entity.Employeeec
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers
import java.util.*

data class EmployeeecInput(
    val id: Int?,
    /**
     * 员工编号
     */
    val eid: Int?,
    /**
     * 奖罚日期
     */
    val ecdate: Date?,
    /**
     * 奖罚原因
     */
    val ecreason: String?,
    /**
     * 奖罚分
     */
    val ecpoint: Int?,
    /**
     * 奖罚类别，0：奖，1：罚
     */
    val ectype: Int?,
    /**
     * 备注
     */
    val remark: String?,
) : Input<Employeeec> {

    override fun toEntity(): Employeeec {
        return CONVERTER.toEmployeeec(this)
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toEmployeeec(input: EmployeeecInput): Employeeec
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}

