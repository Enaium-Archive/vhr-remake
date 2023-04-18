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
import { IDepartment, IEmployee, IJobLevel, INation, IPolitic, IPosition } from "@/model"
import { Employee } from "@/model/type/employee"
import { ref } from "vue"
import { get } from "@/util/reuqest"

const degreeParse = Employee.degreeParse

const Degree = Employee.Degree

const props = defineProps<{
  employee: IEmployee
}>()

const positions = ref<IPosition[]>()
const nations = ref<INation[]>()
const joblevels = ref<IJobLevel[]>()
const politics = ref<IPolitic[]>()
const departments = ref<IDepartment[]>()

get<IPosition[]>("/employee/position").then((r) => {
  positions.value = r.metadata
})

get<INation[]>("/employee/nation").then((r) => {
  nations.value = r.metadata
})

get<IJobLevel[]>("/employee/jobLevel").then((r) => {
  joblevels.value = r.metadata
})

get<IPolitic[]>("/employee/politic").then((r) => {
  politics.value = r.metadata
})

get<IDepartment[]>("/employee/department").then((r) => {
  departments.value = r.metadata
})
</script>

<template>
  <ElForm>
    <ElRow>
      <ElCol :span="6">
        <ElFormItem label="姓名:" prop="name">
          <ElInput
            size="small"
            style="width: 150px"
            prefix-icon="el-icon-edit"
            v-model="props.employee.name"
            placeholder="请输入员工姓名"
          ></ElInput>
        </ElFormItem>
      </ElCol>
      <ElCol :span="5">
        <ElFormItem label="性别:" prop="gender">
          <ElRadioGroup v-model="props.employee.gender">
            <ElRadio label="男">男</ElRadio>
            <ElRadio label="女">女</ElRadio>
          </ElRadioGroup>
        </ElFormItem>
      </ElCol>
      <ElCol :span="6">
        <ElFormItem label="出生日期:" prop="birthday">
          <ElDatePicker
            v-model="props.employee.birthday"
            size="small"
            type="date"
            value-format="yyyy-MM-dd"
            style="width: 150px"
            placeholder="出生日期"
          >
          </ElDatePicker>
        </ElFormItem>
      </ElCol>
      <ElCol :span="7">
        <ElFormItem label="政治面貌:" prop="politicId">
          <ElSelect v-model="props.employee.politicId" placeholder="政治面貌" size="small" style="width: 200px">
            <ElOption v-for="item in politics" :key="item.id" :label="item.name" :value="item.id" />
          </ElSelect>
        </ElFormItem>
      </ElCol>
    </ElRow>
    <ElRow>
      <ElCol :span="6">
        <ElFormItem label="民族:" prop="nationId">
          <ElSelect v-model="props.employee.nationId" placeholder="民族" size="small" style="width: 150px">
            <ElOption v-for="item in nations" :key="item.id" :label="item.name" :value="item.id" />
          </ElSelect>
        </ElFormItem>
      </ElCol>
      <ElCol :span="5">
        <ElFormItem label="籍贯:" prop="nativePlace">
          <ElInput
            size="small"
            style="width: 120px"
            prefix-icon="el-icon-edit"
            v-model="props.employee.nativePlace"
            placeholder="请输入籍贯"
          ></ElInput>
        </ElFormItem>
      </ElCol>
      <ElCol :span="6">
        <ElFormItem label="电子邮箱:" prop="email">
          <ElInput
            size="small"
            style="width: 150px"
            prefix-icon="el-icon-message"
            v-model="props.employee.email"
            placeholder="请输入电子邮箱"
          ></ElInput>
        </ElFormItem>
      </ElCol>
      <ElCol :span="7">
        <ElFormItem label="联系地址:" prop="address">
          <ElInput
            size="small"
            style="width: 200px"
            prefix-icon="el-icon-edit"
            v-model="props.employee.address"
            placeholder="请输入联系地址"
          ></ElInput>
        </ElFormItem>
      </ElCol>
    </ElRow>
    <ElRow>
      <ElCol :span="6">
        <ElFormItem label="职位:" prop="posId">
          <ElSelect v-model="props.employee.posId" placeholder="职位" size="small" style="width: 150px">
            <ElOption v-for="item in positions" :key="item.id" :label="item.name" :value="item.id" />
          </ElSelect>
        </ElFormItem>
      </ElCol>
      <ElCol :span="5">
        <ElFormItem label="职称:" prop="jobLevelId">
          <ElSelect v-model="props.employee.jobLevelId" placeholder="职称" size="small" style="width: 150px">
            <ElOption v-for="item in joblevels" :key="item.id" :label="item.name" :value="item.id" />
          </ElSelect>
        </ElFormItem>
      </ElCol>
      <ElCol :span="6">
        <ElFormItem label="所属部门:" prop="departmentId">
          <ElSelect v-model="props.employee.departmentId" placeholder="部门" size="small" style="width: 150px">
            <ElOption v-for="item in departments" :key="item" :label="item.name" :value="item.id" />
          </ElSelect>
        </ElFormItem>
      </ElCol>
      <ElCol :span="7">
        <ElFormItem label="电话号码:" prop="phone">
          <ElInput
            size="small"
            style="width: 200px"
            prefix-icon="el-icon-phone"
            v-model="props.employee.phone"
            placeholder="电话号码"
          ></ElInput>
        </ElFormItem>
      </ElCol>
    </ElRow>
    <ElRow>
      <ElCol :span="6">
        <ElFormItem label="工号:" prop="workID">
          <ElInput
            size="small"
            style="width: 150px"
            prefix-icon="el-icon-edit"
            v-model="props.employee.workId"
            placeholder="工号"
            disabled
          ></ElInput>
        </ElFormItem>
      </ElCol>
      <ElCol :span="5">
        <ElFormItem label="学历:" prop="tiptopDegree">
          <ElSelect v-model="props.employee.tiptopDegree" placeholder="学历" size="small" style="width: 150px">
            <ElOption v-for="item in Degree" :key="item" :label="degreeParse(<Employee.Degree>item)" :value="item" />
          </ElSelect>
        </ElFormItem>
      </ElCol>
      <ElCol :span="6">
        <ElFormItem label="毕业院校:" prop="school">
          <ElInput
            size="small"
            style="width: 150px"
            prefix-icon="el-icon-edit"
            v-model="props.employee.school"
            placeholder="毕业院校名称"
          ></ElInput>
        </ElFormItem>
      </ElCol>
      <ElCol :span="7">
        <ElFormItem label="专业名称:" prop="specialty">
          <ElInput
            size="small"
            style="width: 200px"
            prefix-icon="el-icon-edit"
            v-model="props.employee.specialty"
            placeholder="请输入专业名称"
          ></ElInput>
        </ElFormItem>
      </ElCol>
    </ElRow>
    <ElRow>
      <ElCol :span="6">
        <ElFormItem label="入职日期:" prop="beginDate">
          <ElDatePicker
            v-model="props.employee.beginDate"
            size="small"
            type="date"
            value-format="yyyy-MM-dd"
            style="width: 130px"
            placeholder="入职日期"
          >
          </ElDatePicker>
        </ElFormItem>
      </ElCol>
      <ElCol :span="6">
        <ElFormItem label="转正日期:" prop="conversionTime">
          <ElDatePicker
            v-model="props.employee.conversionTime"
            size="small"
            type="date"
            value-format="yyyy-MM-dd"
            style="width: 130px"
            placeholder="转正日期"
          >
          </ElDatePicker>
        </ElFormItem>
      </ElCol>
      <ElCol :span="6">
        <ElFormItem label="合同起始日期:" prop="beginContract">
          <ElDatePicker
            v-model="props.employee.beginContract"
            size="small"
            type="date"
            value-format="yyyy-MM-dd"
            style="width: 130px"
            placeholder="合同起始日期"
          >
          </ElDatePicker>
        </ElFormItem>
      </ElCol>
      <ElCol :span="6">
        <ElFormItem label="合同终止日期:" prop="endContract">
          <ElDatePicker
            v-model="props.employee.endContract"
            size="small"
            type="date"
            value-format="yyyy-MM-dd"
            style="width: 150px"
            placeholder="合同终止日期"
          >
          </ElDatePicker>
        </ElFormItem>
      </ElCol>
    </ElRow>
    <ElRow>
      <ElCol :span="8">
        <ElFormItem label="身份证号码:" prop="idCard">
          <ElInput
            size="small"
            style="width: 180px"
            prefix-icon="el-icon-edit"
            v-model="props.employee.idCard"
            placeholder="请输入身份证号码"
          ></ElInput>
        </ElFormItem>
      </ElCol>
      <ElCol :span="8">
        <ElFormItem label="聘用形式:" prop="engageForm">
          <ElRadioGroup v-model="props.employee.engageForm">
            <ElRadio label="劳动合同">劳动合同</ElRadio>
            <ElRadio label="劳务合同">劳务合同</ElRadio>
          </ElRadioGroup>
        </ElFormItem>
      </ElCol>
      <ElCol :span="8">
        <ElFormItem label="婚姻状况:" prop="wedlock">
          <ElRadioGroup v-model="props.employee.wedlock">
            <ElRadio label="已婚">已婚</ElRadio>
            <ElRadio label="未婚">未婚</ElRadio>
            <ElRadio label="离异">离异</ElRadio>
          </ElRadioGroup>
        </ElFormItem>
      </ElCol>
    </ElRow>
  </ElForm>
</template>

<style scoped></style>