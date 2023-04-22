package cn.enaium.vhr.repository

import cn.enaium.vhr.model.entity.*
import cn.enaium.vhr.model.entity.input.HrInput
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.ilike
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface HrRepository : KRepository<Hr, Int> {
    fun findByUsername(username: String): Hr?
    fun findByHr(hrInput: HrInput): List<Hr> {
        return sql.createQuery(Hr::class) {
            hrInput.name?.takeIf { it.isNotEmpty() }?.let { where(table.name ilike it) }
            hrInput.phone?.takeIf { it.isNotEmpty() }?.let { where(table.phone ilike it) }
            hrInput.telephone?.takeIf { it.isNotEmpty() }?.let { where(table.telephone ilike it) }
            hrInput.address?.takeIf { it.isNotEmpty() }?.let { where(table.address ilike it) }
            hrInput.username?.takeIf { it.isNotEmpty() }?.let { where(table.username ilike it) }
            select(table.fetchBy {
                allScalarFields()
                roles { allScalarFields() }
            })
        }.execute()
    }
}

