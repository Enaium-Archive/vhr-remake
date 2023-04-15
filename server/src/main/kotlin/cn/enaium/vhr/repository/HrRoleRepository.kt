package cn.enaium.vhr.repository

import cn.enaium.vhr.entity.HrRole
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface HrRoleRepository : KRepository<HrRole, Int> {

}

