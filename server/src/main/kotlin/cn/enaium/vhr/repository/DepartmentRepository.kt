package cn.enaium.vhr.repository

import cn.enaium.vhr.entity.Department
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface DepartmentRepository : KRepository<Department, Int> {

}

