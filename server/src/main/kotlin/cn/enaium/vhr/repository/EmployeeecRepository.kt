package cn.enaium.vhr.repository

import cn.enaium.vhr.model.entity.Employeeec
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface EmployeeecRepository : KRepository<Employeeec, Int> {

}

