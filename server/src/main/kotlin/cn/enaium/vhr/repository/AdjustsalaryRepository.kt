package cn.enaium.vhr.repository

import cn.enaium.vhr.entity.Adjustsalary
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface AdjustsalaryRepository : KRepository<Adjustsalary, Int> {

}

