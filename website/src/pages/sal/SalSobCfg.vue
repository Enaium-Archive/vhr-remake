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
import { IEmployee, IPage, ISalary } from "@/model"
import { get } from "@/util/reuqest"
import SalSobEdit from "@/components/salary/SalSobEdit.vue"

const employees = ref<IPage<IEmployee>>({})
const salaries = ref<ISalary[]>()
const page = ref(1)
const size = ref(10)
const loading = ref(false)
const salSobDialog = ref(false)
const currentEmployee = ref<IEmployee>({})

const initEmployee = () => {
  loading.value = true
  get<IPage<IEmployee>>(`/employee?page=${page.value - 1}&size=${size.value}`).then((r) => {
    loading.value = false
    employees.value = r.metadata
  })
}

initEmployee()

get<ISalary[]>("/salary").then((r) => {
  salaries.value = r.metadata
})

const handleSizeChange = (val: number) => {
  size.value = val
  initEmployee()
}
const handleCurrentChange = (val: number) => {
  page.value = val
  initEmployee()
}

const edit = (employee: IEmployee) => {
  currentEmployee.value = employee
  salSobDialog.value = true
}
</script>

<template>
  <ElTable
    :data="employees.content"
    v-loading="loading"
    element-loading-text="正在加载..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
    border
    stripe
    size="small"
  >
    <ElTableColumn type="selection" align="left" width="55" />
    <ElTableColumn prop="name" label="姓名" fixed width="120" align="left" />
    <ElTableColumn prop="workID" label="工号" width="120" align="left" />
    <ElTableColumn prop="email" label="电子邮件" width="200" align="left" />
    <ElTableColumn prop="phone" label="电话号码" width="120" align="left" />
    <ElTableColumn prop="department.name" label="所属部门" width="120" align="left" />
    <ElTableColumn label="所属部门" align="center">
      <template #default="scope">
        <ElTooltip placement="right" v-if="scope.row.salary">
          <template #content>
            <table>
              <tr>
                <td>基本工资</td>
                <td>{{ scope.row.salary.basicSalary }}</td>
              </tr>
              <tr>
                <td>交通补助</td>
                <td>{{ scope.row.salary.trafficSalary }}</td>
              </tr>
              <tr>
                <td>午餐补助</td>
                <td>{{ scope.row.salary.lunchSalary }}</td>
              </tr>
              <tr>
                <td>奖金</td>
                <td>{{ scope.row.salary.bonus }}</td>
              </tr>
              <tr>
                <td>养老金比率</td>
                <td>{{ scope.row.salary.pensionPer }}</td>
              </tr>
              <tr>
                <td>养老金基数</td>
                <td>{{ scope.row.salary.pensionBase }}</td>
              </tr>
              <tr>
                <td>医疗保险比率</td>
                <td>{{ scope.row.salary.medicalPer }}</td>
              </tr>
              <tr>
                <td>医疗保险基数</td>
                <td>{{ scope.row.salary.medicalBase }}</td>
              </tr>
              <tr>
                <td>公积金比率</td>
                <td>{{ scope.row.salary.accumulationFundPer }}</td>
              </tr>
              <tr>
                <td>公积金基数</td>
                <td>{{ scope.row.salary.accumulationFundBase }}</td>
              </tr>
              <tr>
                <td>启用时间</td>
                <td>{{ scope.row.salary.createDate }}</td>
              </tr>
            </table>
          </template>
          <ElTag>{{ scope.row.salary.name }}</ElTag>
        </ElTooltip>
        <ElTag v-else>暂未设置</ElTag>
      </template>
    </ElTableColumn>
    <ElTableColumn label="操作" align="center">
      <template #default="scope">
        <ElButton type="primary" @click="edit(scope.row)">修改工资账套</ElButton>
      </template>
    </ElTableColumn>
  </ElTable>
  <div class="d-flex justify-content-end">
    <ElPagination
      background
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      :total="employees.totalElements"
      @currentChange="handleCurrentChange"
      @sizeChange="handleSizeChange"
    />
  </div>
  <ElDialog v-model="salSobDialog">
    <SalSobEdit :employee="currentEmployee" @done="salSobDialog = false" />
  </ElDialog>
</template>

<style scoped></style>