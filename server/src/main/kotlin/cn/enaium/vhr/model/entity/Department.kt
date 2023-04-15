package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*


/**
 * @author Enaium
 */
@Entity
@Table(name = "department")
interface Department {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  部门名称 */
    val name: String?

    /**
     *  parentId
     */
    val parentId: Int?

    /**
     *  depPath
     */
    val depPath: String?

    /**
     *  enabled
     */
    val enabled: Int?

    /**
     *  isParent
     */
    val isParent: Int?
}
