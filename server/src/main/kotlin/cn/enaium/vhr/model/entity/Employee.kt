package cn.enaium.vhr.model.entity

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
    val wedlock: String?

    /**
     *  民族 */
    val nationId: Int?

    /**
     *  籍贯 */
    val nativePlace: String?

    /**
     *  政治面貌 */
    val politicId: Int?

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

    /**
     *  职称ID */
    val jobLevelId: Int?

    /**
     *  职位ID */
    val posId: Int?

    /**
     *  聘用形式 */
    val engageForm: String?

    /**
     *  最高学历 */
    val tiptopDegree: String?

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
    val workState: String?

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
