package cn.enaium.vhr.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDate


/**
 * @author Enaium
 */
@Entity
@Table(name = "appraise")
interface Appraise {

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
     *  考评日期 */
    val appDate: LocalDate?

    /**
     *  考评结果 */
    val appResult: String?

    /**
     *  考评内容 */
    val appContent: String?

    /**
     *  备注 */
    val remark: String?
}
