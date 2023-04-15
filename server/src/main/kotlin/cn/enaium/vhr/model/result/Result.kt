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

package cn.enaium.vhr.model.result

/**
 * @author Enaium
 *
 * @param T ；类型
 * @property code 请求代码
 * @property message 请求信息
 * @property metadata 请求数据
 */
data class Result<T>(val code: Int, val message: String, val metadata: T) {

    enum class Status(val code: Int, val message: String) {
        SUCCESS(200, "成功"),
        FAIL(999, "失败"),
    }

    object Builder {
        fun <T> success(
            status: Status = Status.SUCCESS,
            code: Int = status.code,
            message: String = status.message,
            metadata: T? = null
        ): Result<T?> {
            return Result(code, message, metadata)
        }

        fun <T> fail(
            status: Status = Status.FAIL,
            code: Int = status.code,
            message: String = status.message,
            metadata: T? = null
        ): Result<T?> {
            return Result(code, message, metadata)
        }
    }
}
