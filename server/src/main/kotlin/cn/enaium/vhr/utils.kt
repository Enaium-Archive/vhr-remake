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

import org.apache.poi.hssf.usermodel.HSSFCell
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

/**
 * 将Excel转为单元格
 *
 * 三维数组,一维是每每个工作表,二维是每行,三维是每个单元格\
 *
 * @param columnOffset 列偏移
 * @param rowOffset 航偏移
 */
fun excel2Cell(
    hssfWorkbook: HSSFWorkbook,
    columnOffset: Int = 0,
    rowOffset: Int = 0
): Array<Array<Array<HSSFCell?>?>?> {
    val sheets = arrayOfNulls<Array<Array<HSSFCell?>?>>(hssfWorkbook.numberOfSheets)
    for (i in 0 until hssfWorkbook.numberOfSheets) {
        val sheet = hssfWorkbook.getSheetAt(i)
        val tables = arrayOfNulls<Array<HSSFCell?>>(sheet.physicalNumberOfRows - columnOffset)
        for (j in columnOffset until sheet.physicalNumberOfRows) {
            val row = sheet.getRow(j)
            val rows = arrayOfNulls<HSSFCell>(row.physicalNumberOfCells - rowOffset)
            for (k in rowOffset until row.physicalNumberOfCells) {
                rows[k - rowOffset] = row.getCell(k)//将单元格添加到行中
            }
            tables[j - columnOffset] = rows//将每一行添加到表中
        }
        sheets[i] = tables//将每个表添加到工作表中
    }
    return sheets
}

inline fun <reified T : Enum<T>> getEnumByOrigin(origin: String?): T? {
    return origin?.let {
        enumValues<T>().firstOrNull {
            val field = it.javaClass.getDeclaredField("origin").apply { isAccessible = true }
            field.get(it) == origin
        }
    }
}

