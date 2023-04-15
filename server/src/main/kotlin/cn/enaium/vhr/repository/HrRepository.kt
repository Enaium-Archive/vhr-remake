package cn.enaium.vhr.repository

import cn.enaium.vhr.model.entity.Hr
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface HrRepository : KRepository<Hr, Int> {
    fun findByUsername(username: String): Hr?
}

