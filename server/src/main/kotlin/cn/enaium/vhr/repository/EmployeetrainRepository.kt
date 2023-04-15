package cn.enaium.vhr.repository

import cn.enaium.vhr.entity.Employeetrain
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface EmployeetrainRepository : KRepository<Employeetrain, Int> {

}

