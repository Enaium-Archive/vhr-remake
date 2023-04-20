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
import { Plus, Refresh } from "@element-plus/icons-vue"
import { ref } from "vue"
import { ISalary } from "@/model"
import { del, get } from "@/util/reuqest"
import { ElMessage, ElMessageBox } from "element-plus"
import SalaryForm from "@/components/salary/SalaryForm.vue"

const salaries = ref<ISalary[]>()
const salaryDialog = ref(false)
const activeItemIndex = ref(0)
const currentSalary = ref<ISalary>({})

const initSalary = () => {
  get<ISalary[]>("/salary").then((r) => {
    salaries.value = r.metadata
  })
}
initSalary()

const showEditSalary = (salary: ISalary) => {
  currentSalary.value = salary
  salaryDialog.value = true
}

const deleteSalary = (salary: ISalary) => {
  ElMessageBox.confirm(`此操作将永久删除【${salary.name}】, 是否继续?`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      del(`/salary/${salary.id}`).then((r) => {
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

const addSalary = () => {
  currentSalary.value = {}
  salaryDialog.value = true
}
</script>

<template>
  <div class="d-flex justify-content-between mt-1">
    <ElButton @click="addSalary">
      <template #icon>
        <Plus />
      </template>
      添加工资账套
    </ElButton>
    <ElButton>
      <template #icon>
        <Refresh @click="initSalary" />
      </template>
    </ElButton>
  </div>
  <div class="mt-1">
    <ElTable :data="salaries">
      <ElTableColumn type="selection" width="55"></ElTableColumn>
      <ElTableColumn width="120" prop="name" label="账套名称"></ElTableColumn>
      <ElTableColumn width="70" prop="basicSalary" label="基本工资"></ElTableColumn>
      <ElTableColumn width="70" prop="trafficSalary" label="交通补助"></ElTableColumn>
      <ElTableColumn width="70" prop="lunchSalary" label="午餐补助"></ElTableColumn>
      <ElTableColumn width="70" prop="bonus" label="奖金"></ElTableColumn>
      <ElTableColumn width="100" prop="createDate" label="启用时间"></ElTableColumn>
      <ElTableColumn label="养老金" align="center">
        <ElTableColumn width="70" prop="pensionPer" label="比率"></ElTableColumn>
        <ElTableColumn width="70" prop="pensionBase" label="基数"></ElTableColumn>
      </ElTableColumn>
      <ElTableColumn label="医疗保险" align="center">
        <ElTableColumn width="70" prop="medicalPer" label="比率"></ElTableColumn>
        <ElTableColumn width="70" prop="medicalBase" label="基数"></ElTableColumn>
      </ElTableColumn>
      <ElTableColumn label="公积金" align="center">
        <ElTableColumn width="70" prop="accumulationFundPer" label="比率"></ElTableColumn>
        <ElTableColumn width="70" prop="accumulationFundBase" label="基数"></ElTableColumn>
      </ElTableColumn>
      <ElTableColumn label="操作">
        <template #default="scope">
          <ElButton @click="showEditSalary(scope.row)">编辑</ElButton>
          <ElButton type="danger" @click="deleteSalary(scope.row)">删除</ElButton>
        </template>
      </ElTableColumn>
    </ElTable>
  </div>
  <ElDialog v-model="salaryDialog">
    <div class="d-flex justify-content-around align-items-center">
      <SalaryForm :salary="currentSalary" @done="" />
    </div>
  </ElDialog>
</template>

<style scoped></style>