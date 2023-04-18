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

import org.babyfish.jimmer.Input;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cn.enaium.vhr.model.entity.Menu;

data class MenuInput(
        val id: Int?,
    
    val url: String?,
    
    val path: String?,
    
    val component: String?,
    
    val name: String?,
    
    val iconCls: String?,
    
    val keepAlive: Int?,
    
    val requireAuth: Int?,
    
    val parentId: Int?,
    
    val enabled: Boolean?,
    
) : Input<Menu> {

    override fun toEntity(): Menu {
        return CONVERTER.toMenu(this);
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toMenu(input: MenuInput): Menu;
    }
    
    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}

