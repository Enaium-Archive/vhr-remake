/*
 * Copyright 2023 Enaium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.enaium.vhr.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author Enaium
 */
@Service
class CaptchaService(val stringRedisTemplate: StringRedisTemplate) {
    /**
     * 存入验证码
     *
     * @param uuid 唯一随机标识
     * @param code 验证码
     */
    fun saveCaptcha(uuid: UUID, code: String) {
        stringRedisTemplate.opsForValue().set("captcha:${uuid}", code, 1, TimeUnit.MINUTES)
    }

    /**
     * 找出验证码
     *
     * @param uuid 唯一随机标识
     * @return 验证码
     */
    fun findCaptcha(uuid: UUID): String? {
        return stringRedisTemplate.opsForValue().get("captcha:${uuid}")
    }
}