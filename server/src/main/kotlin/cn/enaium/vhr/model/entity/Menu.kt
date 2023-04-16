package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*


/**
 * @author Enaium
 */
@Entity
@Table(name = "menu")
interface Menu {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  url
     */
    val url: String?

    /**
     *  path
     */
    val path: String?

    /**
     *  component
     */
    val component: String?

    /**
     *  name
     */
    val name: String?

    /**
     *  iconCls
     */
    val iconCls: String?

    /**
     *  keepAlive
     */
    val keepAlive: Int?

    /**
     *  requireAuth
     */
    val requireAuth: Int?

    @Key
    @ManyToOne
    val parent: Menu?

    /**
     * 自关联
     */
    @OneToMany(mappedBy = "parent", orderedProps = [OrderedProp("name")])
    val children: List<Menu>

    /**
     *  enabled
     */
    val enabled: Boolean?

    /**
     * Menu多对多Role
     */
    @ManyToMany
    @JoinTable(
        name = "menu_role",//中间表名
        joinColumnName = "mid",//Menu的ID
        inverseJoinColumnName = "rid"//Role的ID
    )
    val roles: List<Role>
}
