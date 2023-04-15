package cn.enaium.vhr.repository

import cn.enaium.vhr.entity.Empsalary
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface EmpsalaryRepository : KRepository<Empsalary, Int> {

}

