package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime


/**
 * @author Enaium
 */
@Entity
@Table(name = "msgcontent")
interface Msgcontent {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  title
     */
    val title: String?

    /**
     *  message
     */
    val message: String?

    /**
     *  createDate
     */
    val createDate: LocalDateTime
}
