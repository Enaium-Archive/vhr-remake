package cn.enaium.vhr.repository

import cn.enaium.vhr.entity.Employee
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface EmployeeRepository : KRepository<Employee, Int> {

}

