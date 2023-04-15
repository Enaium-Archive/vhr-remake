package cn.enaium.vhr.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime


/**
 * @author Enaium
 */
@Entity
@Table(name = "position")
interface Position {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  职位 */
    @Key
    val name: String?

    /**
     *  createDate
     */
    val createDate: LocalDateTime?

    /**
     *  enabled
     */
    val enabled: Int?
}
