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

package cn.enaium.vhr.controller.system

import cn.enaium.vhr.model.entity.Position
import cn.enaium.vhr.model.entity.input.PositionInput
import cn.enaium.vhr.model.result.Result
import cn.enaium.vhr.repository.PositionRepository
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/system/position")
class PositionController(
    val positionRepository: PositionRepository
) {
    @GetMapping
    fun get(): Result<List<Position>?> {
        return Result.Builder.success(metadata = positionRepository.findAll())
    }

    @PutMapping
    fun put(@RequestBody position: PositionInput): Result<Nothing?> {
        position.createDate = LocalDateTime.now()
        positionRepository.save(position)
        return Result.Builder.success()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Result<Nothing?> {
        positionRepository.deleteById(id)
        return Result.Builder.success()
    }
}