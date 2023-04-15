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

import cn.hutool.captcha.CaptchaUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/captcha")
class CaptchaController {
    @GetMapping
    fun get(request: HttpServletRequest, response: HttpServletResponse) {
        val captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4)
        response.contentType = "image/jpeg"
        captcha.write(response.outputStream)
        request.getSession(true).setAttribute("code", captcha.code)
        response.outputStream.close()
    }
}