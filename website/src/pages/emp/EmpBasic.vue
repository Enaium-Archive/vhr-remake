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
import { FormRules } from "element-plus"
import { IEmployee, IPage } from "@/model"
import { get } from "@/util/reuqest"
import { Employee } from "@/model/type/employee"
import EmployeeForm from "@/components/EmployeeForm.vue"

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

const formRule = reactive<FormRules>({
  name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  gender: [{ required: true, message: "请输入性别", trigger: "blur" }],
  birthday: [{ required: true, message: "请输入出生日期", trigger: "blur" }],
  idCard: [
    { required: true, message: "请输入身份证号码", trigger: "blur" },
    {
      pattern:
        /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
      message: "身份证号码格式不正确",
      trigger: "blur",
    },
  ],
  wedlock: [{ required: true, message: "请输入婚姻状况", trigger: "blur" }],
  nationId: [{ required: true, message: "请输入您组", trigger: "blur" }],
  nativePlace: [{ required: true, message: "请输入籍贯", trigger: "blur" }],
  politicId: [{ required: true, message: "请输入政治面貌", trigger: "blur" }],
  email: [
    { required: true, message: "请输入邮箱地址", trigger: "blur" },
    {
      type: "email",
      message: "邮箱格式不正确",
      trigger: "blur",
    },
  ],
  phone: [{ required: true, message: "请输入电话号码", trigger: "blur" }],
  address: [{ required: true, message: "请输入员工地址", trigger: "blur" }],
  departmentId: [{ required: true, message: "请输入部门名称", trigger: "blur" }],
  jobLevelId: [{ required: true, message: "请输入职称", trigger: "blur" }],
  posId: [{ required: true, message: "请输入职位", trigger: "blur" }],
  engageForm: [{ required: true, message: "请输入聘用形式", trigger: "blur" }],
  tiptopDegree: [{ required: true, message: "请输入学历", trigger: "blur" }],
  specialty: [{ required: true, message: "请输入专业", trigger: "blur" }],
  school: [{ required: true, message: "请输入毕业院校", trigger: "blur" }],
  beginDate: [{ required: true, message: "请输入入职日期", trigger: "blur" }],
  workState: [{ required: true, message: "请输入工作状态", trigger: "blur" }],
  workID: [{ required: true, message: "请输入工号", trigger: "blur" }],
  contractTerm: [{ required: true, message: "请输入合同期限", trigger: "blur" }],
  conversionTime: [{ required: true, message: "请输入转正日期", trigger: "blur" }],
  notworkDate: [{ required: true, message: "请输入离职日期", trigger: "blur" }],
  beginContract: [{ required: true, message: "请输入合同起始日期", trigger: "blur" }],
  endContract: [{ required: true, message: "请输入合同结束日期", trigger: "blur" }],
  workAge: [{ required: true, message: "请输入工龄", trigger: "blur" }],
})

const emps = ref<IPage<IEmployee>>()
const keyword = ref<string>()
const currentEmployee = ref<IEmployee>()
const showEditEmployee = ref(false)

const initEmps = (type?: string) => {
  loading.value = true
  let url = "/employee?"
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
    emps.value = r.metadata
  })
}

initEmps()

const editEmployee = (employee: IEmployee) => {
  currentEmployee.value = employee
  showEditEmployee.value = true
}

const updateEmployee = () => {
  showEditEmployee.value = false
}
</script>

<template>
  <ElTable
    :data="emps.content"
    stripe
    border
    v-loading="loading"
    element-loading-text="正在加载..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
    style="width: 100%"
    v-if="emps"
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
        <el-button style="padding: 3px" size="small" type="danger">删除</el-button>
      </template>
    </ElTableColumn>
  </ElTable>
  <ElDialog v-model="showEditEmployee">
    <ElForm>
      <EmployeeForm :employee="currentEmployee" v-if="currentEmployee"/>
    </ElForm>
    <template #footer>
      <el-button @click="showEditEmployee = false">取消</el-button>
      <el-button type="primary" @click="updateEmployee">确定</el-button>
    </template>
  </ElDialog>
</template>

<style scoped></style>