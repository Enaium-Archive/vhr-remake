package cn.enaium.vhr.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDate


/**
 * @author Enaium
 */
@Entity
@Table(name = "employeeec")
interface Employeeec {

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
     *  奖罚日期 */
    val ecDate: LocalDate?

    /**
     *  奖罚原因 */
    val ecReason: String?

    /**
     *  奖罚分 */
    val ecPoint: Int?

    /**
     *  奖罚类别，0：奖，1：罚 */
    val ecType: Int?

    /**
     *  备注 */
    val remark: String?
}
