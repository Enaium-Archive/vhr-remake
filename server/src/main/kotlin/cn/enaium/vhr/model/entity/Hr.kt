package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*


/**
 * @author Enaium
 */
@Entity
@Table(name = "hr")
interface Hr {

    /**
     *  hrID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  姓名 */
    val name: String?

    /**
     *  手机号码 */
    val phone: String?

    /**
     *  住宅电话 */
    val telephone: String?

    /**
     *  联系地址 */
    val address: String?

    /**
     *  enabled
     */
    val enabled: Boolean?

    /**
     *  用户名 */
    val username: String?

    /**
     *  密码 */
    val password: String?

    /**
     *  userface
     */
    val userface: String?

    /**
     *  remark
     */
    val remark: String?

    /**
     * HR多对多Role
     */
    @ManyToMany
    @JoinTable(
        name = "hr_role",//中间表名
        joinColumnName = "hrid",//HR的ID
        inverseJoinColumnName = "rid"//Role的ID
    )
    val roles: List<Role>
}
