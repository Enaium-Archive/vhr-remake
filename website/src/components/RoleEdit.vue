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
import { get, put } from "@/util/reuqest"
import { IHr, IRole } from "@/model"
import { reactive, ref, watch } from "vue"
import { ElMessage, FormInstance, FormRules } from "element-plus"

const props = defineProps<{
  hr: IHr
}>()

const emit = defineEmits(["done"])

const done = () => {
  emit("done")
}

const roles = ref<IRole[]>()
get<IRole[]>("/system/role").then((r) => {
  roles.value = r.metadata
})

const form = reactive<{
  roles?: number[]
}>({})
const formRef = ref<FormInstance>()
const formRule = reactive<FormRules>({
  roles: [{ required: true, message: "请选择角色", trigger: ["blur", "change"] }],
})
const submit = (instance: FormInstance | undefined) => {
  if (!instance) return
  instance.validate((valid) => {
    if (valid) {
      put(`/hr/${props.hr.id}/roleIds?roleIds=${form.roles}`).then((r) => {
        if (r.code == 200) {
          ElMessage({ type: "success", message: "更新成功" })
          done()
        }
      })
    }
  })
}

const refresh = () => {
  form.roles = props.hr.roles?.map((it) => it.id!)
}

refresh()

watch(
  () => props.hr,
  () => {
    refresh()
  },
)
</script>

<template>
  <ElForm :model="form" :rules="formRule" ref="formRef">
    <ElFormItem prop="roles" label="角色">
      <ElSelect class="w-100" v-model="form.roles" multiple placeholder="请选择角色">
        <ElOption v-model="props.hr.roles" v-for="role in roles" :label="role.nameZh" :value="role.id" :key="role.id" />
      </ElSelect>
    </ElFormItem>
  </ElForm>
  <div class="d-flex justify-content-end">
    <ElButton @click="submit(formRef)" type="primary">完成</ElButton>
  </div>
</template>

<style scoped></style>