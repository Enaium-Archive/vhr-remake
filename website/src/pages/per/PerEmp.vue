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
import { IEmployee, IPage } from "@/util/model"

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

const initEmps = (type: string) => {
  loading.value = true
  
}
</script>

<template></template>

<style scoped></style>