package cn.enaium.vhr.model.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDate


/**
 * @author Enaium
 */
@Entity
@Table(name = "mail_send_log")
interface MailSendLog {

    /**
     *  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     *  msgId
     */
    val msgId: String?

    /**
     *  empId
     */
    val empId: Int?

    /**
     *  0发送中，1发送成功，2发送失败 */
    val status: Int?

    /**
     *  routeKey
     */
    val routeKey: String?

    /**
     *  exchange
     */
    val exchange: String?

    /**
     *  重试次数 */
    val count: Int?

    /**
     *  第一次重试时间 */
    val tryTime: LocalDate?

    /**
     *  createTime
     */
    val createTime: LocalDate?

    /**
     *  updateTime
     */
    val updateTime: LocalDate?
}
