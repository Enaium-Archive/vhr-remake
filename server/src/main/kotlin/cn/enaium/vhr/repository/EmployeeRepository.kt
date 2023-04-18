package cn.enaium.vhr.repository

import cn.enaium.vhr.model.entity.*
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.between
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDate

/**
 * @author Enaium
 */
@Repository
interface EmployeeRepository : KRepository<Employee, Int> {
    fun findAllByEmployee(pageable: Pageable, employee: Employee?, beginDateScope: Array<Long>?): Page<Employee> {
        return pager(pageable).execute(sql.createQuery(Employee::class) {
            employee?.let { employee ->
                employee.politicId.takeIf { it != null }?.let { where(table.politicId eq it) }
                employee.nationId.takeIf { it != null }?.let { where(table.nationId eq it) }
                employee.jobLevelId.takeIf { it != null }?.let { where(table.jobLevelId eq it) }
                employee.posId.takeIf { it != null }?.let { where(table.posId eq it) }
                employee.engageForm.takeIf { it != null }?.let { where(table.engageForm eq it) }
                employee.departmentId.takeIf { it != null }?.let { where(table.departmentId eq it) }
                employee.name.takeIf { it != null }?.let { where(table.name eq it) }
            }
            beginDateScope?.let { beginDateScope ->
                beginDateScope.takeIf { it.size == 2 }?.let {
                    where(
                        table.beginDate.between(
                            LocalDate.ofEpochDay(it[0]),
                            LocalDate.ofEpochDay(it[1])
                        )
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

