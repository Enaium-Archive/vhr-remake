package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime


/**
 * @author Enaium
 */
@Entity
@Table(name = "salary")
interface Salary {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  基本工资 */
    val basicSalary: Int?

    /**
     *  奖金 */
    val bonus: Int?

    /**
     *  午餐补助 */
    val lunchSalary: Int?

    /**
     *  交通补助 */
    val trafficSalary: Int?

    /**
     *  应发工资 */
    val allSalary: Int?

    /**
     *  养老金基数 */
    val pensionBase: Int?

    /**
     *  养老金比率 */
    val pensionPer: Double?

    /**
     *  启用时间 */
    val createDate: LocalDateTime?

    /**
     *  医疗基数 */
    val medicalBase: Int?

    /**
     *  医疗保险比率 */
    val medicalPer: Double?

    /**
     *  公积金基数 */
    val accumulationFundBase: Int?

    /**
     *  公积金比率 */
    val accumulationFundPer: Double?

    /**
     *  name
     */
    val name: String?

    /**
     * 一对多
     */
    @OneToMany(mappedBy = "salary")
    val employees: List<Employee>
}
