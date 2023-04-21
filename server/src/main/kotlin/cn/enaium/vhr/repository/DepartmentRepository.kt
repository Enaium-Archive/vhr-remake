package cn.enaium.vhr.repository

import cn.enaium.vhr.model.entity.Department
import cn.enaium.vhr.model.entity.fetchBy
import cn.enaium.vhr.model.entity.parent
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.table.isNull
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface DepartmentRepository : KRepository<Department, Int> {
    fun findTree(): List<Department> = sql.createQuery(Department::class) {
        where(table.parent.isNull())
        select(table.fetchBy {
            allScalarFields()
            children({
                recursive()
            }) {
                allScalarFields()
                parent {
                    allScalarFields()
                }
            }
        })
    }.execute()
}

