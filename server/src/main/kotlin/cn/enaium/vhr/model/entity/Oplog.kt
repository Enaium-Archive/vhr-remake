package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDate


/**
 * @author Enaium
 */
@Entity
@Table(name = "oplog")
interface Oplog {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  添加日期 */
    val addDate: LocalDate?

    /**
     *  操作内容 */
    val operate: String?

    /**
     *  操作员ID */
    val hrid: Int?
}
