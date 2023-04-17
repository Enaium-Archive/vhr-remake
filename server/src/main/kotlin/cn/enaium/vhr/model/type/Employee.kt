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

package cn.enaium.vhr.model.type

import org.babyfish.jimmer.sql.EnumItem

/**
 * @author Enaium
 */
class Employee {
    enum class Wedlock {
        @EnumItem(name = "已婚")
        MARRIED,

        @EnumItem(name = "未婚")
        SINGLE,

        @EnumItem(name = "离异")
        DIVORCED
    }

    enum class Degree {
        @EnumItem(name = "博士")
        DOCTOR,

        @EnumItem(name = "硕士")
        MASTER,

        @EnumItem(name = "本科")
        BEN,

        @EnumItem(name = "专科")
        ZHUAN,

        @EnumItem(name = "高中")
        HEIGHT,

        @EnumItem(name = "初中")
        MIDDLE,

        @EnumItem(name = "小学")
        LOW,

        @EnumItem(name = "替他")
        OTHER
    }

    enum class State {
        @EnumItem(name = "在职")
        STAY,

        @EnumItem(name = "离职")
        LEAVING
    }
}