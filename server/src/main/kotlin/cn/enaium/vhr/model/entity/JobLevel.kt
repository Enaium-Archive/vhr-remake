package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime


/**
 * @author Enaium
 */
@Entity
@Table(name = "joblevel")
interface JobLevel {

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
    val enabled: Boolean?

    @OneToMany(mappedBy = "department")
    val employees: List<Employee>
}
