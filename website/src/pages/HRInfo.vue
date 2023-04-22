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
import { IHr } from "@/model"
import { useUserStore } from "@/store"

const userStore = useUserStore()

const infoForm = reactive<{
  oldPassword?: string
  password?: string
  checkPassword?: string
}>({})

const infoRef = ref<FormInstance>()

const validatePass = (rule: any, value: any, callback: any) => {
  if (infoForm.checkPassword !== "") {
    if (!infoRef.value) return
    infoRef.value.validateField("checkPassword", () => null)
  }
  callback()
}
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value !== infoForm.password) {
    callback(new Error("两次密码不一致!"))
  } else {
    callback()
  }
}

const infoRule = reactive<FormRules>({
  oldPassword: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { validator: validatePass, trigger: "blur" },
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { validator: validatePass, trigger: "blur" },
  ],
  checkPassword: [
    { required: true, message: "请再次输入密码", trigger: "blur" },
    { validator: validatePass2, trigger: "blur" },
  ],
})

const hr = ref<IHr>()

const initHr = () => {
  get<IHr>(`/hr/${userStore.id}`).then((r) => {
    hr.value = r.metadata
  })
}

initHr()

const visibleEditInfo = ref(false)
const visibleEditPassword = ref(false)

const update = () => {
  put("/hr", hr.value).then((r) => {
    if (r.code == 200) {
      ElMessage({ type: "success", message: "修改成功" })
    }
  })
}

const updatePassword = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid, fields) => {
    if (valid) {
      hr.value!.oldPassword = infoForm.oldPassword
      hr.value!.password = infoForm.password
      update()
    } else {
      console.log("error submit!", fields)
    }
  })
}
</script>

<template>
  <div v-if="hr">
    <ElCard class="w-25">
      <template #header>
        <span>{{ hr.name }}</span>
      </template>
      <div>
        <div class="d-flex justify-content-center">
          <ElUpload :show-file-list="false" :on-success="initHr" :data="hr">
            <img
              title="点击修改用户头像"
              :src="hr.userface"
              alt="avatar"
              style="width: 100px; height: 100px; border-radius: 50px"
            />
          </ElUpload>
        </div>
        <div>
          电话号码
          <ElTag>
            {{ hr.telephone }}
          </ElTag>
        </div>
        <div>
          手机号码
          <ElTag>
            {{ hr.phone }}
          </ElTag>
        </div>
        <div>
          居住地址
          <ElTag>
            {{ hr.address }}
          </ElTag>
        </div>
        <div>
          用户标签
          <ElTag type="success" style="margin-left: 5px" v-for="role in hr.roles" :key="role.id">
            {{ role.nameZh }}
          </ElTag>
        </div>
        <div class="d-flex justify-content-around mt-1">
          <ElButton type="primary" @click="visibleEditInfo = true">修改信息</ElButton>
          <ElButton type="danger" @click="visibleEditPassword = true">修改密码</ElButton>
        </div>
      </div>
    </ElCard>

    <ElDialog v-model="visibleEditInfo">
      <ElForm>
        <ElFormItem label="用户昵称">
          <ElInput v-model="hr.name" />
        </ElFormItem>
        <ElFormItem label="电话号码">
          <ElInput v-model="hr.telephone" />
        </ElFormItem>
        <ElFormItem label="手机号码">
          <ElInput v-model="hr.phone" />
        </ElFormItem>
        <ElFormItem label="用户地址">
          <ElInput v-model="hr.address" />
        </ElFormItem>
      </ElForm>

      <template #footer>
        <ElButton @click="visibleEditInfo = false">取消</ElButton>
        <ElButton type="primary" @click="update">确定</ElButton>
      </template>
    </ElDialog>

    <ElDialog v-model="visibleEditPassword">
      <ElForm :model="infoForm" status-icon :rules="infoRule" ref="infoRef" label-width="120px">
        <ElFormItem prop="oldPassword" label="请输入旧密码">
          <ElInput v-model="infoForm.oldPassword" />
        </ElFormItem>
        <ElFormItem prop="password" label="请输入新密码">
          <ElInput v-model="infoForm.password" />
        </ElFormItem>
        <ElFormItem prop="checkPassword" label="新确认密码">
          <ElInput v-model="infoForm.checkPassword" />
        </ElFormItem>
      </ElForm>

      <template #footer>
        <ElButton @click="visibleEditPassword = false">取消</ElButton>
        <ElButton type="primary" @click="updatePassword(infoRef)">确定</ElButton>
      </template>
    </ElDialog>
  </div>
</template>

<style scoped></style>