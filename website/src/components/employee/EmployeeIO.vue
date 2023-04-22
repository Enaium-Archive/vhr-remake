<!--
  - Copyright 2023 Enaium
  -
  - Licensed under the Apache License, Version 2.0 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     http://www.apache.org/licenses/LICENSE-2.0
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<script setup lang="ts">
import { ref } from "vue"
import { ElMessage, UploadFile, UploadFiles, UploadRequestOptions } from "element-plus"
import EmployeeEdit from "@/components/employee/EmployeeEdit.vue"
import { IEmployee } from "@/model"
import http from "@/util/http"
import { Download, Loading, Plus, Upload } from "@element-plus/icons-vue"

const importDataBtnText = ref("导入数据")
const importDataBtnIcon = ref(Upload)
const importDataDisabled = ref(false)
const showEditEmployee = ref(false)

const employee = ref<IEmployee>({})

const exportData = () => {
  http
    .get("/employee/export", {
      responseType: "blob",
    })
    .then((r) => {
      const link = document.createElement("a")
      let blob = new Blob([r.data], { type: "application/vnd.ms-excel" })
      link.style.display = "none"
      link.href = URL.createObjectURL(blob)
      let num = ""
      for (let i = 0; i < 10; i++) {
        num += Math.ceil(Math.random() * 10)
      }
      link.setAttribute("download", num + ".xls")
      document.body.appendChild(link)
      link.click()
      document.body.removeChild(link)
    })
}

const beforeUpload = () => {
  importDataBtnText.value = "正在导入"
  importDataBtnIcon.value = Loading
  importDataDisabled.value = true
}

const upload = (options: UploadRequestOptions) => {
  const formData = new FormData()
  formData.append("file", options.file)
  http
    .put("/employee/import", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then((r: any) => {
      if (r.data.code == 200) {
        ElMessage({ type: "success", message: "导入成功" })
        importDataBtnText.value = "导入数据"
        importDataBtnIcon.value = Upload
        importDataDisabled.value = false
      } else {
        importDataBtnText.value = "导入数据"
        importDataBtnIcon.value = Upload
        importDataDisabled.value = false
      }
    })
}

const addEmployee = () => {
  employee.value = {}
  showEditEmployee.value = true
}
</script>

<template>
  <div class="d-flex align-items-center">
    <ElUpload
      :show-file-list="false"
      :before-upload="beforeUpload"
      :disabled="importDataDisabled"
      style="display: inline-flex; margin-right: 8px"
      :http-request="upload"
    >
      <ElButton :disabled="importDataDisabled" type="success" :icon="importDataBtnIcon">
        {{ importDataBtnText }}
      </ElButton>
    </ElUpload>
    <ElButton type="success" @click="exportData">
      <template #icon>
        <Download />
      </template>
      导出数据
    </ElButton>
    <ElButton type="primary" @click="addEmployee">
      <template #icon>
        <Plus />
      </template>
      添加员工
    </ElButton>
  </div>
  <ElDialog v-model="showEditEmployee">
    <EmployeeEdit :employee="employee" />
  </ElDialog>
</template>

<style scoped></style>