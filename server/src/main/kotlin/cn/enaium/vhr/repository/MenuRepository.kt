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
package cn.enaium.vhr.repository

import cn.enaium.vhr.model.entity.*
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.or
import org.babyfish.jimmer.sql.kt.ast.table.isNull
import org.springframework.stereotype.Repository

/**
 * 数据交互类
 *
 * @author Enaium
 */
@Repository
interface MenuRepository : KRepository<Menu, Int> {
    fun findAllByHrId(hrId: Int): List<Menu> {
        return sql.createQuery(Menu::class) {
            where(table.parent.isNull())
            select(table.fetchBy {
                allScalarFields()
                children({
                    recursive()
                    filter {
                        where(
                            table.enabled eq true,
                            or(
                                table.asTableEx().roles.isNull(),
                                table.asTableEx().roles.hrs.id eq hrId
                            )
                        )
                    }
                }) {
                    allScalarFields()
                    roles {
                        allScalarFields()
                    }
                }
            })
        }.execute()
    }

    fun findTree(): List<Menu> = sql.createQuery(Menu::class) {
        where(table.parent.isNull())
        select(table.fetchBy {
            allScalarFields()
            children({ recursive() }) {
                allScalarFields()
            }
            parent {
                allScalarFields()
            }
        })
    }.execute()
}
