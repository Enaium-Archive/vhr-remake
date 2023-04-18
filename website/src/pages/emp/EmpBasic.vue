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
import { reactive, ref } from "vue"
import { IEmployee, IPage } from "@/model"
import { del, get } from "@/util/reuqest"
import { Employee } from "@/model/type/employee"
import EmployeeForm from "@/components/EmployeeForm.vue"
import { ElMessage, ElMessageBox } from "element-plus"

const wedlockParse = Employee.wedlockParse
const degreeParse = Employee.degreeParse

const loading = ref(false)

const searchForm = reactive<{
  politicId?: undefined
  nationId?: undefined
  jobLevelId?: undefined
  posId?: undefined
  engageForm?: undefined
  departmentId?: undefined
  beginDateScope?: undefined
}>({})

const employees = ref<IPage<IEmployee>>()
const keyword = ref<string>()
const currentEmployee = ref<IEmployee>()
const showEditEmployee = ref(false)
const page = ref(1)
const size = ref(10)

const initEmployee = (type?: string) => {
  loading.value = true
  let url = `/employee?page=${page.value - 1}&size=${size.value}`
  if (type && type == "advanced") {
    if (searchForm.politicId) {
      url += "&politicId=" + searchForm.politicId
    }
    if (searchForm.nationId) {
      url += "&nationId=" + searchForm.nationId
    }
    if (searchForm.jobLevelId) {
      url += "&jobLevelId=" + searchForm.jobLevelId
    }
    if (searchForm.posId) {
      url += "&posId=" + searchForm.posId
    }
    if (searchForm.engageForm) {
      url += "&engageForm=" + searchForm.engageForm
    }
    if (searchForm.departmentId) {
      url += "&departmentId=" + searchForm.departmentId
    }
    if (searchForm.beginDateScope) {
      url += "&beginDateScope=" + searchForm.beginDateScope
    }
  } else if (keyword.value) {
    url += "&name=" + keyword.value
  }
  get<IPage<IEmployee>>(url).then((r) => {
    loading.value = false
    employees.value = r.metadata
  })
}

initEmployee()

const editEmployee = (employee: IEmployee) => {
  currentEmployee.value = employee
  showEditEmployee.value = true
}

const deleteEmployee = (employee: IEmployee) => {
  ElMessageBox.confirm(`此操作将永久删除【${employee.name}】, 是否继续?`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      del(`/employee/${employee.id}`).then((r) => {
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

const handleSizeChange = (val: number) => {
  size.value = val
  initEmployee()
}
const handleCurrentChange = (val: number) => {
  page.value = val
  initEmployee()
}
</script>

<template>
  <div>
    <div class="d-flex justify-content-between">

    </div>
    <div>
      <transition>

      </transition>
    </div>
  </div>

  <div class="mt-1">
    <ElTable
      :data="employees.content"
      stripe
      border
      v-loading="loading"
      element-loading-text="正在加载..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      style="width: 100%"
      v-if="employees"
    >
      <ElTableColumn type="selection" width="55" />
      <ElTableColumn prop="name" fixed align="left" label="姓名" width="90" />
      <ElTableColumn prop="workID" label="工号" align="left" width="85" />
      <ElTableColumn prop="gender" label="性别" align="left" width="85" />
      <ElTableColumn prop="birthday" width="95" align="left" label="出生日期" />
      <ElTableColumn prop="idCard" width="150" align="left" label="身份证号码" />
      <ElTableColumn prop="wedlock" width="70" label="婚姻状况">
        <template #default="scope">
          {{ wedlockParse(scope.row.wedlock) }}
        </template>
      </ElTableColumn>
      <ElTableColumn prop="nation.name" width="50" label="民族" />
      <ElTableColumn prop="nativePlace" width="80" label="籍贯" />
      <ElTableColumn prop="politic.name" label="政治面貌" />
      <ElTableColumn prop="email" width="180" align="left" label="电子邮件" />
      <ElTableColumn prop="phone" width="100" align="left" label="电话号码" />
      <ElTableColumn prop="address" width="220" align="left" label="联系地址" />
      <ElTableColumn prop="department.name" width="100" align="left" label="所属部门" />
      <ElTableColumn prop="pos.name" width="100" label="职位" />
      <ElTableColumn prop="jobLevel.name" width="100" label="职称" />
      <ElTableColumn prop="engageForm" width="100" align="left" label="聘用形式" />
      <ElTableColumn prop="tiptopDegree" width="80" align="left" label="最高学历">
        <template #default="scope">
          {{ degreeParse(scope.row.tiptopDegree) }}
        </template>
      </ElTableColumn>
      <ElTableColumn prop="specialty" width="150" align="left" label="专业" />
      <ElTableColumn prop="school" width="150" align="left" label="毕业院校" />
      <ElTableColumn prop="beginDate" width="95" align="left" label="入职日期" />
      <ElTableColumn prop="conversionTime" width="95" align="left" label="转正日期" />
      <ElTableColumn prop="beginContract" width="95" align="left" label="合同起始日期" />
      <ElTableColumn prop="endContract" width="95" align="left" label="合同截止日期" />
      <ElTableColumn width="100" align="left" label="合同期限">
        <template #default="scope">
          <el-tag>{{ scope.row.contractTerm }}</el-tag>
          年
        </template>
      </ElTableColumn>
      <ElTableColumn fixed="right" width="200" label="操作">
        <template #default="scope">
          <el-button style="padding: 3px" size="small" @click="editEmployee(scope.row)">编辑</el-button>
          <el-button style="padding: 3px" size="small" type="primary">查看高级资料</el-button>
          <el-button style="padding: 3px" size="small" type="danger" @click="deleteEmployee(scope.row)">删除</el-button>
        </template>
      </ElTableColumn>
    </ElTable>
    <div class="d-flex justify-content-end">
      <ElPagination
        background
        layout="sizes, prev, pager, next, jumper, ->, total, slot"
        :total="employees?.totalElements"
        @currentChange="handleCurrentChange"
        @sizeChange="handleSizeChange"
      />
    </div>
  </div>
  <ElDialog v-model="showEditEmployee">
    <EmployeeForm :employee="currentEmployee" v-if="currentEmployee" />
  </ElDialog>
</template>

<style scoped></style>