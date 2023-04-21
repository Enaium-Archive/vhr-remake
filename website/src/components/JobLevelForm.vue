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
import { IJobLevel } from "@/model"
import { reactive, ref } from "vue"
import { ElMessage, FormInstance, FormRules } from "element-plus"
import { get, put } from "@/util/reuqest"
import { JobLevel } from "@/model/type/JobLevel"

const levelParse = JobLevel.levelParse

const props = defineProps<{
  jobLevel: IJobLevel
}>()

const emit = defineEmits(["done"])

const rule = reactive<FormRules>({
  name: [{ required: true, message: "请输入职称", trigger: "blur" }],
  titleLevel: [{ required: true, message: "请选择等级", trigger: "blur" }],
})
const formRef = ref<FormInstance | undefined>()

const submit = (instance: FormInstance | undefined) => {
  if (!instance) return
  instance.validate((valid) => {
    if (valid) {
      put("/system/jobLevel", props.jobLevel).then((r) => {
        if (r.code == 200) {
          ElMessage({ type: "success", message: "更新成功" })
        }
      })
      emit("done")
    }
  })
}

const jobLevels = ref<IJobLevel[]>()

get<IJobLevel[]>("/system/jobLevel").then((r) => {
  jobLevels.value = r.metadata
})
</script>

<template>
  <ElForm :model="props.jobLevel" :rules="rule" ref="formRef">
    <ElFormItem prop="name" label="职称">
      <ElInput v-model="props.jobLevel.name" />
    </ElFormItem>
    <ElFormItem prop="titleLevel" label="等级">
      <ElSelect v-model="props.jobLevel.titleLevel">
        <ElOption v-for="j in jobLevels" :label="levelParse(j.titleLevel)" :value="j.titleLevel" />
      </ElSelect>
    </ElFormItem>
    <ElFormItem prop="enable" label="是否启用">
      <ElSwitch v-model="props.jobLevel.enabled"/>
    </ElFormItem>
    <ElFormItem>
      <ElButton type="primary" @click="submit(formRef)">完成</ElButton>
    </ElFormItem>
  </ElForm>
</template>

<style scoped></style>