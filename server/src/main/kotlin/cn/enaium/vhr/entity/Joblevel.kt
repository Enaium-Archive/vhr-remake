package cn.enaium.vhr.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime


/**
 * @author Enaium
 */
@Entity
@Table(name = "joblevel")
interface Joblevel {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  职称名称 */
    val name: String?

    /**
     *  titleLevel
     */
    val titleLevel: String?

    /**
     *  createDate
     */
    val createDate: LocalDateTime?

    /**
     *  enabled
     */
    val enabled: Int?
}
