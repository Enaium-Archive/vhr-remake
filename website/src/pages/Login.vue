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
import { ElMessage, FormInstance, FormRules } from "element-plus"
import { get, put } from "@/util/reuqest"
import { useUserStore } from "@/store"

const userStore = useUserStore()

const loginForm = reactive<{
  username?: string
  password?: string
  code?: string
}>({})

const loginRef = ref<FormInstance>()

const loginRule = reactive<FormRules>({
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
  code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
})

const loading = ref(false)

const captcha = reactive<{
  id?: string
  image?: string
}>({})

const login = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid, fields) => {
    if (valid) {
      loading.value = true

      put<{ id: number; token: string }>("/state", { ...loginForm, captcha: captcha.id }).then((r) => {
        loading.value = false
        if (r.code == 200) {
          userStore.id = r.metadata.id
          userStore.token = r.metadata.token
          ElMessage({ type: "success", message: "登录成功" })
          window.$router.push({ path: "/home" })
        }
      })
    } else {
      console.log("error submit!", fields)
    }
  })
}

/**
 * 刷新验证码
 */
const refreshCaptcha = () => {
  get<{ id?: string; image?: string }>("/captcha").then((r) => {
    captcha.id = r.metadata.id
    captcha.image = r.metadata.image
  })
}

refreshCaptcha()
</script>

<template>
  <!--  设置高度为屏幕高度，接着水平居中，垂直居中-->
  <div class="vh-100 d-flex justify-content-center align-items-center">
    <!--    设置宽度为四分之一-->
    <ElCard class="w-25">
      <template #header> 系统登录</template>
      <ElForm ref="loginRef" :model="loginForm" :rules="loginRule" v-loading="loading">
        <ElFormItem prop="username">
          <ElInput v-model="loginForm.username" placeholder="请输入用户名" />
        </ElFormItem>
        <ElFormItem prop="password">
          <ElInput v-model="loginForm.password" placeholder="请输入用密码" />
        </ElFormItem>
        <ElFormItem prop="code">
          <div class="d-flex h-75">
            <ElInput v-model="loginForm.code" placeholder="点击图片更换验证码" />
            <img :src="captcha.image" alt="" @click="refreshCaptcha" />
          </div>
        </ElFormItem>
        <div class="d-flex justify-content-between">
          <ElCheckbox>记住登录</ElCheckbox>
          <ElButton type="primary" @click="login(loginRef)">登录</ElButton>
        </div>
      </ElForm>
    </ElCard>
  </div>
</template>

<style scoped></style>