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
import { IDepartment, IEmployee, IJobLevel, INation, IPolitic, IPosition } from "@/model";
import { get } from "@/util/reuqest"
import { Search } from "@element-plus/icons-vue"

const props = defineProps<{
  employee: IEmployee
}>()

const emit = defineEmits(["done"])

const done = () => {
  emit("done")
  console.log(props.employee);
}

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
  <div>
    <ElRow>
      <ElCol :span="5">
        政治面貌:
        <ElSelect v-model="props.employee.politicId" placeholder="政治面貌" size="small" style="width: 130px">
          <ElOption v-for="item in politics" :key="item.id" :label="item.name" :value="item.id" />
        </ElSelect>
      </ElCol>
      <ElCol :span="4">
        民族:
        <ElSelect v-model="props.employee.nationId" placeholder="民族" size="small" style="width: 130px">
          <ElOption v-for="item in nations" :key="item.id" :label="item.name" :value="item.id" />
        </ElSelect>
      </ElCol>
      <ElCol :span="4">
        职位:
        <ElSelect v-model="props.employee.posId" placeholder="职位" size="small" style="width: 130px">
          <ElOption v-for="item in positions" :key="item.id" :label="item.name" :value="item.id" />
        </ElSelect>
      </ElCol>
      <ElCol :span="4">
        职称:
        <ElSelect v-model="props.employee.jobLevelId" placeholder="职称" size="small" style="width: 130px">
          <ElOption v-for="item in joblevels" :key="item.id" :label="item.name" :value="item.id" />
        </ElSelect>
      </ElCol>
      <ElCol :span="7">
        聘用形式:
        <ElRadioGroup v-model="props.employee.engageForm">
          <ElRadio label="劳动合同">劳动合同</ElRadio>
          <ElRadio label="劳务合同">劳务合同</ElRadio>
        </ElRadioGroup>
      </ElCol>
    </ElRow>
    <ElRow style="margin-top: 10px">
      <ElCol :span="5">
        所属部门:
        <ElSelect v-model="props.employee.departmentId" placeholder="部门" size="small" style="width: 150px">
          <ElOption v-for="item in departments" :key="item" :label="item.name" :value="item.id" />
        </ElSelect>
      </ElCol>
      <ElCol :span="10">
        入职日期:
        <ElDatePicker
          v-model="props.employee.beginDateScope"
          type="daterange"
          size="small"
          unlink-panels
          value-format="YYYY-MM-DD"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        >
        </ElDatePicker>
      </ElCol>
      <ElCol :span="5" :offset="4">
        <el-button size="small" type="primary" @click="done">
          <template #icon>
            <Search />
          </template>
          搜索
        </el-button>
      </ElCol>
    </ElRow>
  </div>
</template>

<style scoped></style>