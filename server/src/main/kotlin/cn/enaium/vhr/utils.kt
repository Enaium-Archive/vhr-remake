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

package cn.enaium.vhr

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.HorizontalAlignment

/**
 * @author Enaium
 */
fun string2Excel(sheetName: String, titles: Array<String>, values: Array<Array<String>>): HSSFWorkbook {
    val hssfWorkbook = HSSFWorkbook()
    val sheet = hssfWorkbook.createSheet(sheetName)
    var row = sheet.createRow(0)
    val style = hssfWorkbook.createCellStyle()
    style.alignment = HorizontalAlignment.CENTER

    titles.forEachIndexed { index, t ->
        val createCell = row.createCell(index)
        createCell.setCellValue(t)
        createCell.setCellStyle(style)
    }

    values.forEachIndexed { i, line ->
        row = sheet.createRow(i + 1)
        line.forEachIndexed { j, value ->
            row.createCell(j).setCellValue(value)
        }
    }

    return hssfWorkbook
}