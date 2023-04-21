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
import { IJobLevel } from "@/model"
import { del, get } from "@/util/reuqest"
import { JobLevel } from "@/model/type/JobLevel"
import JobLevelForm from "@/components/JobLevelForm.vue"
import { ElMessage, ElMessageBox } from "element-plus"
import { Plus } from "@element-plus/icons-vue";

const levelParse = JobLevel.levelParse

const jobLevels = ref<IJobLevel[]>()
const jobLevelDialog = ref(false)
const currentJobLevel = ref<IJobLevel>({})

get<IJobLevel[]>("/system/jobLevel").then((r) => {
  jobLevels.value = r.metadata
})

const add = () => {
  currentJobLevel.value = {}
  jobLevelDialog.value = true
}

const edit = (jobLevel: IJobLevel) => {
  currentJobLevel.value = jobLevel
  jobLevelDialog.value = true
}
const remove = (jobLevel: IJobLevel) => {
  ElMessageBox.confirm(`此操作将永久删除【${jobLevel.name}】, 是否继续?`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      del(`/system/jobLevel/${jobLevel.id}`).then((r) => {
        if (r.code == 200) {
          ElMessage({ type: "success", message: "删除成功" })
        }
      })
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消删除",
      })
    })
}
</script>

<template>
  <ElButton @click="add" type="primary" size="small">
    <template #icon>
      <Plus />
    </template>
    添加职称...
  </ElButton>
  <ElTable :data="jobLevels">
    <ElTableColumn type="selection" width="55" />
    <ElTableColumn prop="id" label="编号" width="55" />
    <ElTableColumn prop="name" label="职称名称" width="150" />
    <ElTableColumn prop="titleLevel" label="职称级别">
      <template #default="scope">
        {{ levelParse(scope.row.titleLevel) }}
      </template>
    </ElTableColumn>
    <ElTableColumn prop="createDate" label="创建时间" />
    <ElTableColumn label="是否启用">
      <template #default="scope">
        <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
        <el-tag type="danger" v-else>未启用</el-tag>
      </template>
    </ElTableColumn>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="edit(scope.row)">编辑</el-button>
        <el-button size="small" type="danger" @click="remove(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </ElTable>
  <ElDialog v-model="jobLevelDialog">
    <JobLevelForm :job-level="currentJobLevel" @done="jobLevelDialog = false" />
  </ElDialog>
</template>

<style scoped></style>