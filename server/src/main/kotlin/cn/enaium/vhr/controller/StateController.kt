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
import cn.dev33.satoken.secure.BCrypt
import cn.dev33.satoken.stp.StpUtil
import cn.enaium.vhr.model.request.StateRequest
import cn.enaium.vhr.model.response.StateResponse
import cn.enaium.vhr.model.result.ResponseResult
import cn.enaium.vhr.repository.HrRepository
import cn.enaium.vhr.service.CaptchaService
import org.springframework.web.bind.annotation.*

/**
 * @author Enaium
 * @property hrRepository HR的数据访问
 */
@SaIgnore
@RestController
@RequestMapping("/state")
class StateController(
    val hrRepository: HrRepository,
    val captchaService: CaptchaService
) {
    /**
     * 登录
     *
     * @param body
     * @see cn.enaium.vhr.model.response.StateResponse
     * @see cn.enaium.vhr.model.request.StateRequest
     */
    @PutMapping
    fun put(@RequestBody body: StateRequest): ResponseResult<Any?> {
        if (body.code != captchaService.findCaptcha(body.captcha)) {
            return ResponseResult.Builder.fail(status = ResponseResult.Status.CAPTCHA_INCORRECT)
        }

        hrRepository.findByUsername(body.username)?.let { hr ->
            hr.password.takeIf {
                BCrypt.checkpw(body.password, hr.password)//判断密码是否正确
            }?.let {//正确这个值不为null
                return ResponseResult.Builder.success(
                    metadata = StateResponse(hr.id, StpUtil.createLoginSession(hr.id))
                )
            } ?: let {//不正确这个值为null
                return ResponseResult.Builder.fail(status = ResponseResult.Status.PASSWORD_INCORRECT)
            }
        }
        return ResponseResult.Builder.fail(status = ResponseResult.Status.USERNAME_DOESNT_EXIST)
    }

    /**
     * 退出登录
     */
    @DeleteMapping
    fun delete(): ResponseResult<Nothing?> {
        StpUtil.logout()
        return ResponseResult.Builder.success()
    }
}