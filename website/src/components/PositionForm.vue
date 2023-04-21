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
import { IPosition } from "@/model"
import { reactive, ref } from "vue"
import { ElMessage, FormInstance, FormRules } from "element-plus"
import { put } from "@/util/reuqest"

const props = defineProps<{
  position: IPosition
}>()

const emit = defineEmits(["done"])

const rule = reactive<FormRules>({
  name: [
    {
      required: true,
      message: "请输入部门名称",
      trigger: "blur",
    },
  ],
})
const formRef = ref<FormInstance | undefined>()

const submit = (instance: FormInstance | undefined) => {
  if (!instance) return
  instance.validate((valid) => {
    if (valid) {
      put("/system/position", props.position).then((r) => {
        if (r.code == 200) {
          ElMessage({ type: "success", message: "更新成功" })
        }
      })
      emit("done")
    }
  })
}
</script>

<template>
  <ElForm :model="props.position" :rules="rule" ref="formRef">
    <ElFormItem prop="name" label="部门名称">
      <ElInput v-model="props.position.name" />
    </ElFormItem>
    <ElFormItem>
      <ElButton type="primary" @click="submit(formRef)">完成</ElButton>
    </ElFormItem>
  </ElForm>
</template>

<style scoped></style>