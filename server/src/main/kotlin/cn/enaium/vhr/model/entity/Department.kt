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
    val name: String

    @ManyToOne
    val parent: Department?

    /**
     * 自关联
     */
    @OneToMany(mappedBy = "parent", orderedProps = [OrderedProp("name")])
    val children: List<Department>

    /**
     *  hasParent
     */
    val hasParent: Boolean?

    /**
     *  depPath
     */
    val depPath: String?

    /**
     *  enabled
     */
    val enabled: Boolean?


    @OneToMany(mappedBy = "department")
    val employees: List<Employee>
}
