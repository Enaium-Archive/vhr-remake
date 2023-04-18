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

import cn.enaium.vhr.model.entity.Employeetrain;

data class EmployeetrainInput(
        val id: Int?,
    
    /**
     * 员工编号
     */
    val eid: Int?,
    
    /**
     * 培训日期
     */
    val trainDate: Date?,
    
    /**
     * 培训内容
     */
    val trainContent: String?,
    
    /**
     * 备注
     */
    val remark: String?,
    
) : Input<Employeetrain> {

    override fun toEntity(): Employeetrain {
        return CONVERTER.toEmployeetrain(this);
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toEmployeetrain(input: EmployeetrainInput): Employeetrain;
    }
    
    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}

