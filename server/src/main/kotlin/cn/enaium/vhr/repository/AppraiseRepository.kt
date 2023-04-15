package cn.enaium.vhr.repository

import cn.enaium.vhr.entity.Appraise
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface AppraiseRepository : KRepository<Appraise, Int> {

}

