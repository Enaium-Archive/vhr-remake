package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*


/**
 * @author Enaium
 */
@Entity
@Table(name = "empsalary")
interface Empsalary {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  eid
     */
    @Key
    val eid: Int?

    /**
     *  sid
     */
    val sid: Int?
}
