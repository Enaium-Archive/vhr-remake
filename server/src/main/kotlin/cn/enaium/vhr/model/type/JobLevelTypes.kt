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
enum class Level {
    @EnumItem(name = "正高级")
    HEIGHT,

    @EnumItem(name = "副高级")
    PRE_HEIGHT,

    @EnumItem(name = "中级")
    MIDDLE,
    @EnumItem(name = "初级")
    LOW,
    @EnumItem(name = "员级")
    NOOB
}