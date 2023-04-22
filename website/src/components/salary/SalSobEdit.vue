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
import { IEmployee, ISalary } from "@/model"
import { ref } from "vue"
import { get, put } from "@/util/reuqest"
import { ElMessage } from "element-plus"

const props = defineProps<{
  employee: IEmployee
}>()

const emit = defineEmits(["done"])

const salaries = ref<ISalary[]>()
const currentSalary = ref<ISalary>()

get<ISalary[]>("/salary").then((r) => {
  salaries.value = r.metadata
})

const done = () => {
  let url = `/employee/${props.employee.id}/salary`

  if (currentSalary.value) {
    url += `?salary=${currentSalary.value}`
  }

  put(`${url}`).then((r) => {
    if (r.code == 200) {
      ElMessage({ type: "success", message: "修改成功" })
    }
  })
  emit("done")
}
</script>

<template>
  <ElForm>
    <ElFormItem prop="salary" label="工资账套">
      <ElSelect v-model="currentSalary">
        <ElOption v-for="salary in salaries" :label="salary.name" :value="salary.id" />
      </ElSelect>
    </ElFormItem>
    <div class="d-flex justify-content-end">
      <ElButton type="primary" @click="done"> 完成</ElButton>
    </div>
  </ElForm>
</template>

<style scoped></style>