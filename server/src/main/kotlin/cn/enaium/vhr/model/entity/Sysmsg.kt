package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*


/**
 * @author Enaium
 */
@Entity
@Table(name = "sysmsg")
interface Sysmsg {
    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  消息id */
    val mid: Int?

    /**
     *  0表示群发消息 */
    val type: Int?

    /**
     *  这条消息是给谁的 */
    val hrid: Int?

    /**
     *  0 未读 1 已读 */
    val state: Int?
}
