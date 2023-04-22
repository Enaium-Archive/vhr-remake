package cn.enaium.vhr.repository

import cn.enaium.vhr.model.entity.*
import cn.enaium.vhr.model.entity.input.EmployeeInput
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.between
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ilike
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDate

/**
 * @author Enaium
 */
@Repository
interface EmployeeRepository : KRepository<Employee, Int> {
    fun findAllByEmployee(
        pageable: Pageable,
        employee: EmployeeInput?,
        beginDateScope: Array<LocalDate>?
    ): Page<Employee> {
        return pager(pageable).execute(sql.createQuery(Employee::class) {
            employee?.let { employee ->
                employee.politicId?.let { where(table.politicId eq it) }
                employee.nationId?.let { where(table.nationId eq it) }
                employee.jobLevelId?.let { where(table.jobLevelId eq it) }
                employee.posId?.let { where(table.posId eq it) }
                employee.engageFrom?.let { where(table.engageFrom eq it) }
                employee.departmentId?.let { where(table.departmentId eq it) }
                employee.name?.takeIf { it.isNotEmpty() }?.let { where(table.name ilike it) }
            }
            beginDateScope?.let { beginDateScope ->
                beginDateScope.takeIf { it.size == 2 }?.let {
                    where(
                        table.beginDate.between(it[0], it[1])
                    )
                }
            }
            select(table.fetchBy {
                allScalarFields()
                nation {
                    allScalarFields()
                }
                politic {
                    allScalarFields()
                }
                department {
                    allScalarFields()
                }
                jobLevel {
                    allScalarFields()
                }
                pos {
                    allScalarFields()
                }
            })
        })
    }
}

