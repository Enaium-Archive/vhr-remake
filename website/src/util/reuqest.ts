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

import { IResult } from "@/model"
import http from "@/util/http"

export const get = async <T>(url: string): Promise<IResult<T>> => {
  return (await http.get(url)).data
}

export const put = async <T>(url: string, data: any = null): Promise<IResult<T>> => {
  return (await http.put(url, data)).data
}

export const patch = async <T>(url: string, data: any): Promise<IResult<T>> => {
  return (await http.patch(url, data)).data
}

export const del = async <T>(url: string): Promise<IResult<T>> => {
  return (await http.delete(url)).data
}