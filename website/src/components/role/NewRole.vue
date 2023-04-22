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
import { Plus } from "@element-plus/icons-vue"
import { reactive, ref } from "vue"
import { IRole } from "@/model"
import { ElMessage, FormInstance, FormRules } from "element-plus"
import { put } from "@/util/reuqest"

const form = reactive<IRole>({})
const formRef = ref<FormInstance>()
const formRule = reactive<FormRules>({
  name: [{ required: true, message: "请输入角色英文名", trigger: "blur" }],
  nameZh: [{ required: true, message: "请输入角色中文名", trigger: "blur" }],
})

const submit = (instance: FormInstance | undefined) => {
  if (!instance) return
  instance.validate((valid) => {
    if (valid) {
      put("/system/role", form).then((r) => {
        if (r.code == 200) {
          ElMessage({ type: "success", message: "添加成功" })
        }
      })
    }
  })
}
</script>

<template>
  <ElForm :model="form" :rules="formRule" ref="formRef">
    <div class="d-flex">
      <ElFormItem prop="name">
        <ElInput placeholder="请输入角色英文名" v-model="form.name">
          <template #prepend>ROLE_</template>
        </ElInput>
      </ElFormItem>
      <ElFormItem prop="nameZh">
        <ElInput placeholder="请输入角色中文名" v-model="form.nameZh" />
      </ElFormItem>
      <ElFormItem>
        <ElButton type="primary" @click="submit(formRef)">
          <template #icon>
            <Plus />
          </template>
          添加角色
        </ElButton>
      </ElFormItem>
    </div>
  </ElForm>
</template>

<style scoped></style>