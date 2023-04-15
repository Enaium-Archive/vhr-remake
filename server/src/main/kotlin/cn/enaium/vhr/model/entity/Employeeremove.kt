package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDate


/**
 * @author Enaium
 */
@Entity
@Table(name = "employeeremove")
interface Employeeremove {

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
     *  调动后部门 */
    val afterDepId: Int?

    /**
     *  调动后职位 */
    val afterJobId: Int?

    /**
     *  调动日期 */
    val removeDate: LocalDate?

    /**
     *  调动原因 */
    val reason: String?

    /**
     *  remark
     */
    val remark: String?
}
