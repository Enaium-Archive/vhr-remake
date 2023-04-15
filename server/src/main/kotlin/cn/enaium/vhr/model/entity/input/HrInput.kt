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

import cn.enaium.vhr.model.entity.Hr
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

data class HrInput(
    /**
     * hrID
     */
    val id: Int?,
    /**
     * 姓名
     */
    val name: String?,
    /**
     * 手机号码
     */
    val phone: String?,
    /**
     * 住宅电话
     */
    val telephone: String?,
    /**
     * 联系地址
     */
    val address: String?,
    val enabled: Int?,
    /**
     * 用户名
     */
    val username: String?,
    /**
     * 密码
     */
    val password: String?,
    val userface: String?,
    val remark: String?,
) : Input<Hr> {

    override fun toEntity(): Hr {
        return CONVERTER.toHr(this)
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toHr(input: HrInput): Hr
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}
