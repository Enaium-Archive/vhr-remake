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
import java.util.Date;

import org.babyfish.jimmer.Input;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cn.enaium.vhr.model.entity.Salary;

data class SalaryInput(
        val id: Int?,
    
    /**
     * 基本工资
     */
    val basicSalary: Int?,
    
    /**
     * 奖金
     */
    val bonus: Int?,
    
    /**
     * 午餐补助
     */
    val lunchSalary: Int?,
    
    /**
     * 交通补助
     */
    val trafficSalary: Int?,
    
    /**
     * 应发工资
     */
    val allSalary: Int?,
    
    /**
     * 养老金基数
     */
    val pensionBase: Int?,
    
    /**
     * 养老金比率
     */
    val pensionPer: Float?,
    
    /**
     * 启用时间
     */
    val createDate: Date?,
    
    /**
     * 医疗基数
     */
    val medicalBase: Int?,
    
    /**
     * 医疗保险比率
     */
    val medicalPer: Float?,
    
    /**
     * 公积金基数
     */
    val accumulationFundBase: Int?,
    
    /**
     * 公积金比率
     */
    val accumulationFundPer: Float?,
    
    val name: String?,
    
) : Input<Salary> {

    override fun toEntity(): Salary {
        return CONVERTER.toSalary(this);
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toSalary(input: SalaryInput): Salary;
    }
    
    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}

