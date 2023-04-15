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

package cn.enaium.vhr.model.entity.input

import cn.enaium.vhr.model.entity.Appraise
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers
import java.util.*

data class AppraiseInput(
    val id: Int?,
    val eid: Int?,
    /**
     * 考评日期
     */
    val appdate: Date?,
    /**
     * 考评结果
     */
    val appresult: String?,
    /**
     * 考评内容
     */
    val appcontent: String?,
    /**
     * 备注
     */
    val remark: String?,
) : Input<Appraise> {

    override fun toEntity(): Appraise {
        return CONVERTER.toAppraise(this)
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toAppraise(input: AppraiseInput): Appraise
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}

