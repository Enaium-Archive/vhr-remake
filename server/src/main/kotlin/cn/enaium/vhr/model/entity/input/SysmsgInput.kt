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

import cn.enaium.vhr.model.entity.Sysmsg
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

data class SysmsgInput(
    val id: Int?,

    /**
     * 消息id
     */
    val mid: Int?,

    /**
     * 0表示群发消息
     */
    val type: Int?,

    /**
     * 这条消息是给谁的
     */
    val hrid: Int?,

    /**
     * 0 未读 1 已读
     */
    val state: Int?,

    ) : Input<Sysmsg> {

    override fun toEntity(): Sysmsg {
        return CONVERTER.toSysmsg(this);
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toSysmsg(input: SysmsgInput): Sysmsg;
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}

