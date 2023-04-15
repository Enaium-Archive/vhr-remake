package cn.enaium.vhr.entity

import org.babyfish.jimmer.sql.*


/**
 * @author Enaium
 */
@Entity
@Table(name = "hr_role")
interface HrRole {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  hrid
     */
    val hrid: Int?

    /**
     *  rid
     */
    val rid: Int?
}
