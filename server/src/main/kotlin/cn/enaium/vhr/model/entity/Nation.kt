package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*


/**
 * @author Enaium
 */
@Entity
@Table(name = "nation")
interface Nation {

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
