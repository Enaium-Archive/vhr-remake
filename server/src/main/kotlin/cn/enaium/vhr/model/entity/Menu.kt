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

    /**
     *  parentId
     */
    val parentId: Int?

    /**
     *  enabled
     */
    val enabled: Int?
}
