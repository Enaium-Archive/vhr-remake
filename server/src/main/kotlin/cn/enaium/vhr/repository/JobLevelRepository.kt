package cn.enaium.vhr.repository

import cn.enaium.vhr.model.entity.JobLevel
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * @author Enaium
 */
@Repository
interface JobLevelRepository : KRepository<JobLevel, Int> {

}

