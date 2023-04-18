package cn.enaium.vhr.model.entity

import cn.enaium.vhr.model.type.Degree
import cn.enaium.vhr.model.type.State
import cn.enaium.vhr.model.type.Wedlock
import org.babyfish.jimmer.sql.*
import java.time.LocalDate


/**
 * @author Enaium
 */
@Entity
@Table(name = "employee")
interface Employee {

    /**
     *  员工编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  员工姓名 */
    val name: String?

    /**
     *  性别 */
    val gender: String?

    /**
     *  出生日期 */
    val birthday: LocalDate?

    /**
     *  身份证号 */
    val idCard: String?

    /**
     *  婚姻状况 */
    val wedlock: Wedlock?

    /**
     *  民族 */
    val nationId: Int?

    @ManyToOne
    val nation: Nation

    /**
     *  籍贯 */
    val nativePlace: String?

    /**
     *  政治面貌 */
    val politicId: Int?

    @ManyToOne
    val politic: Politic

    /**
     *  邮箱 */
    val email: String?

    /**
     *  电话号码 */
    val phone: String?

    /**
     *  联系地址 */
    val address: String?

    /**
     *  所属部门 */
    val departmentId: Int?

    @ManyToOne
    val department: Department

    /**
     *  职称ID */
    val jobLevelId: Int?

    @ManyToOne
    val jobLevel: JobLevel

    /**
     *  职位ID */
    val posId: Int?

    @ManyToOne
    val pos: Position

    /**
     *  聘用形式 */
    val engageForm: String?

    /**
     *  最高学历 */
    val tiptopDegree: Degree?

    /**
     *  所属专业 */
    val specialty: String?

    /**
     *  毕业院校 */
    val school: String?

    /**
     *  入职日期 */
    val beginDate: LocalDate?

    /**
     *  在职状态 */
    val workState: State?

    /**
     *  工号 */
    val workID: String?

    /**
     *  合同期限 */
    val contractTerm: String?

    /**
     *  转正日期 */
    val conversionTime: LocalDate?

    /**
     *  离职日期 */
    val notWorkDate: LocalDate?

    /**
     *  合同起始日期 */
    val beginContract: LocalDate?

    /**
     *  合同终止日期 */
    val endContract: LocalDate?

    /**
     *  工龄 */
    val workAge: Int?
}
