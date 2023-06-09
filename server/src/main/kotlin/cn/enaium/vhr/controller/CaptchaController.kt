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

package cn.enaium.vhr.controller

import cn.dev33.satoken.annotation.SaIgnore
import cn.enaium.vhr.model.response.CaptchaResponse
import cn.enaium.vhr.model.result.ResponseResult
import cn.enaium.vhr.service.CaptchaService
import cn.hutool.captcha.CaptchaUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * @author Enaium
 */
@SaIgnore
@RestController
@RequestMapping("/captcha")
class CaptchaController(val captchaService: CaptchaService) {
    @GetMapping
    fun get(): ResponseResult<Any?> {
        val captcha = CaptchaUtil.createShearCaptcha(150, 50, 4, 4)
        val uuid = UUID.randomUUID()
        captchaService.saveCaptcha(uuid, captcha.code)
        return ResponseResult.Builder.success(metadata = CaptchaResponse(uuid, captcha.imageBase64Data))
    }
}