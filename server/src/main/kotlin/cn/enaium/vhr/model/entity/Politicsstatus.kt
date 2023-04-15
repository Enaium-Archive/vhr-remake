package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*


/**
 * @author Enaium
 */
@Entity
@Table(name = "politicsstatus")
interface Politicsstatus {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  name
     */
    val name: String?
}
