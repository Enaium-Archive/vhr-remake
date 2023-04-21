package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime


/**
 * @author Enaium
 */
@Entity
@Table(name = "t_position")
interface Position {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  职位 */
    val name: String?

    /**
     *  createDate
     */
    val createDate: LocalDateTime?

    /**
     *  enabled
     */
    val enabled: Boolean?

    @OneToMany(mappedBy = "pos")
    val employees: List<Employee>
}
