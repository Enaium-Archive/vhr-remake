package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDate


/**
 * @author Enaium
 */
@Entity
@Table(name = "adjustsalary")
interface Adjustsalary {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  eid
     */
    val eid: Int?

    /**
     *  调薪日期 */
    val asDate: LocalDate?

    /**
     *  调前薪资 */
    val beforeSalary: Int?

    /**
     *  调后薪资 */
    val afterSalary: Int?

    /**
     *  调薪原因 */
    val reason: String?

    /**
     *  备注 */
    val remark: String?
}
