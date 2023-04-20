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
enum class Wedlock(val origin: String) {
    @EnumItem(name = "已婚")
    MARRIED("已婚"),

    @EnumItem(name = "未婚")
    UNMARRIED("未婚"),

    @EnumItem(name = "离异")
    DIVORCED("离异");
}


enum class Degree(val origin: String) {
    @EnumItem(name = "博士")
    DOCTOR("博士"),

    @EnumItem(name = "硕士")
    MASTER("硕士"),

    @EnumItem(name = "本科")
    BEN("本科"),

    @EnumItem(name = "大专")
    ZHUAN("大专"),

    @EnumItem(name = "高中")
    HEIGHT("高中"),

    @EnumItem(name = "初中")
    MIDDLE("初中"),

    @EnumItem(name = "小学")
    LOW("小学"),

    @EnumItem(name = "其他")
    OTHER("其他")
}

enum class State(val origin: String) {
    @EnumItem(name = "在职")
    STAY("在职"),

    @EnumItem(name = "离职")
    LEAVING("离职")
}