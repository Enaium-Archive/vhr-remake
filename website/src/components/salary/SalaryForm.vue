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
import { ISalary } from "@/model"
import { reactive, ref } from "vue"
import { ElMessage, FormInstance, FormRules } from "element-plus"
import { put } from "@/util/reuqest"

const props = defineProps<{
  salary: ISalary
}>()

const emit = defineEmits(["done"])

const done = () => {
  emit("done")
}

const formRef = ref<FormInstance>()
const formRule = reactive<FormRules>({
  basicSalary: [{ required: true, message: "请输入基本工资", trigger: "blur" }],
  trafficSalary: [{ required: true, message: "请输入交通补助", trigger: "blur" }],
  lunchSalary: [{ required: true, message: "请输入午餐补助", trigger: "blur" }],
  bonus: [{ required: true, message: "请输入奖金", trigger: "blur" }],
  pensionPer: [{ required: true, message: "请输入养老金比率", trigger: "blur" }],
  pensionBase: [{ required: true, message: "请输入养老金基数", trigger: "blur" }],
  medicalPer: [{ required: true, message: "请输入医疗保险比率", trigger: "blur" }],
  medicalBase: [{ required: true, message: "请输入医疗保险基数", trigger: "blur" }],
  accumulationFundPer: [{ required: true, message: "请输入公积金比率", trigger: "blur" }],
  accumulationFundBase: [{ required: true, message: "请输入公积金基数", trigger: "blur" }],
  name: [{ required: true, message: "请输入账套名称", trigger: "blur" }],
})

const submit = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid, fields) => {
    if (valid) {
      put("/salary", props.salary).then((r) => {
        if (r.code == 200) {
          emit("done")
          ElMessage({ type: "success", message: "更新成功" })
        }
      })
    }
  })
}
</script>

<template>
  <ElForm :model="props.salary" ref="formRef" :rules="formRule" label-width="120">
    <ElRow :gutter="20">
      <ElCol :span="12">
        <ElFormItem prop="basicSalary" label="基本工资">
          <ElInput v-model="props.salary.basicSalary" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="trafficSalary" label="交通补助">
          <ElInput v-model="props.salary.trafficSalary" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="lunchSalary" label="午餐补助">
          <ElInput v-model="props.salary.lunchSalary" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="bonus" label="奖金">
          <ElInput v-model="props.salary.bonus" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="pensionPer" label="养老金比率">
          <ElInput v-model="props.salary.pensionPer" />
        </ElFormItem>
      </ElCol>

      <ElCol :span="12">
        <ElFormItem prop="pensionBase" label="养老金基数">
          <ElInput v-model="props.salary.pensionBase" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="medicalPer" label="医疗保险比率">
          <ElInput v-model="props.salary.medicalPer" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="medicalBase" label="医疗保险基数">
          <ElInput v-model="props.salary.medicalBase" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="accumulationFundPer" label="公积金比率">
          <ElInput v-model="props.salary.accumulationFundPer" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="accumulationFundBase" label="公积金基数">
          <ElInput v-model="props.salary.accumulationFundBase" />
        </ElFormItem>
      </ElCol>
      <ElCol :span="12">
        <ElFormItem prop="name" label="账套名称">
          <ElInput v-model="props.salary.name" />
        </ElFormItem>
      </ElCol>
    </ElRow>

    <ElButton @click="submit(formRef)" type="primary">提交</ElButton>
  </ElForm>
</template>

<style scoped></style>