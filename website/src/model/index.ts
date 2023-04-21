/*
 * Copyright 2023 Enaium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { Employee } from "@/model/type/Employee"
import Wedlock = Employee.Wedlock
import Degree = Employee.Degree
import { JobLevel } from "@/model/type/JobLevel";
import Level = JobLevel.Level;

export interface IResult<T> {
  code: number
  message: string
  metadata: T
}

export interface IPage<T> {
  content: T[]
  totalPages: number
  totalElements: number
  size: number
  number: number
}

export interface IHr {
  id?: number
  name?: string
  phone?: string
  telephone?: string
  address?: string
  enable?: boolean
  username?: string
  password?: string
  oldPassword?: string
  userface?: string
  remark?: string
  roles?: IRole[]
}

export interface IRole {
  id?: number
  name?: string
  nameZh?: string
}

export interface IMenu {
  id?: number
  url?: string
  path?: string
  component?: string
  name?: string
  iconCls?: string
  keepAlive?: boolean
  requireAuth?: boolean
  parentId?: number
  enable?: boolean
  children?: IMenu[]
}

export interface IEmployee {
  id?: number
  name?: string
  gender?: string
  birthday?: string
  idCard?: string
  wedlock?: Wedlock
  nationId?: number
  nativePlace?: string
  politicId?: number
  email?: string
  phone?: string
  address?: string
  departmentId?: number
  department?: IDepartment
  jobLevelId?: number
  jobLevel?: IJobLevel
  posId?: number
  postId?: number
  engageForm?: string
  tiptopDegree?: Degree
  specialty?: string
  school?: string
  beginDate?: string
  beginDateScope?: [number, number]
  workState?: string
  workId?: string
  contractTerm?: string
  conversionTime?: string
  notWorkDate?: string
  beginContract?: string
  endContract?: string
  workAge?: number
}

export interface IDepartment {
  id?: number
  name?: string
  parentId?: number
  parent?: IDepartment
  children?: IDepartment[]
}

export interface IJobLevel {
  id?: number
  name?: string
  titleLevel?: Level
  createDate?: string
  enabled?: boolean
}

export interface INation {
  id?: number
  name?: string
}

export interface IPolitic {
  id?: number
  name?: string
}

export interface IPosition {
  id?: number
  name?: string
  createData?: string
  enable?: boolean
}

export interface ISalary {
  id?: number
  basicSalary?: number
  bonus?: number
  lunchSalary?: number
  trafficSalary?: number
  allSalary?: number
  pensionBase?: number
  pensionPer?: number
  createDate?: string
  medicalBase?: number
  medicalPer?: number
  accumulationFundBase?: number
  accumulationFundPer?: number
  name?: string
}
