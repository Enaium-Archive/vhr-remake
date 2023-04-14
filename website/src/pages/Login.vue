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
</script>

<template>
  <div class="vh-100 d-flex justify-content-center align-items-center">
    <ElCard class="w-25">
      <template #header>
        系统登录
      </template>
      <ElForm>
        <ElFormItem prop="username">
          <ElInput placeholder="请输入用户名" />
        </ElFormItem>
        <ElFormItem prop="password">
          <ElInput placeholder="请输入用密码" />
        </ElFormItem>
        <ElFormItem prop="code">
          <ElInput placeholder="点击图片更换验证码" />
        </ElFormItem>
        <div class="d-flex justify-content-between">
          <ElCheckbox>记住登录</ElCheckbox>
          <ElButton type="primary">登录</ElButton>
        </div>
      </ElForm>
    </ElCard>
  </div>
</template>

<style scoped></style>
