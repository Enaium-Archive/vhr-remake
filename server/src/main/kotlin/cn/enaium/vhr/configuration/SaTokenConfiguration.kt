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

package cn.enaium.vhr.configuration

import cn.dev33.satoken.interceptor.SaInterceptor
import cn.dev33.satoken.router.SaRouter
import cn.dev33.satoken.stp.StpInterface
import cn.dev33.satoken.stp.StpUtil
import cn.enaium.vhr.repository.HrRepository
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * @author Enaium
 */
@Configuration
class SaTokenConfiguration(
    val hrRepository: HrRepository
) : WebMvcConfigurer, StpInterface {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(SaInterceptor {
            SaRouter.match("/**").notMatchMethod("OPTIONS").check { r ->
                StpUtil.checkLogin()
            }
        }).addPathPatterns("/**")
    }

    override fun getPermissionList(loginId: Any, loginType: String): List<String> {
        return emptyList()
    }

    override fun getRoleList(loginId: Any, loginType: String): List<String> {
        hrRepository.findNullable(loginId as Int)?.let { hr ->
            return hr.roles.map { it.name }
        } ?: let { return emptyList() }
    }
}