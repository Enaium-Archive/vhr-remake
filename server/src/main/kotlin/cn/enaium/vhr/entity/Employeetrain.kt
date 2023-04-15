package cn.enaium.vhr.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDate


/**
 * @author Enaium
 */
@Entity
@Table(name = "employeetrain")
interface Employeetrain {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  员工编号 */
    val eid: Int?

    /**
     *  培训日期 */
    val trainDate: LocalDate?

    /**
     *  培训内容 */
    val trainContent: String?

    /**
     *  备注 */
    val remark: String?
}
