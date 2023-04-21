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

export namespace Employee {
  export enum Wedlock {
    MARRIED = "MARRIED",
    UNMARRIED = "UNMARRIED",
    DIVORCED = "DIVORCED",
  }

  export enum Degree {
    DOCTOR = "DOCTOR",
    MASTER = "MASTER",
    BEN = "BEN",
    ZHUAN = "ZHUAN",
    HEIGHT = "HEIGHT",
    MIDDLE = "MIDDLE",
    LOW = "LOW",
    OTHER = "OTHER",
  }

  export enum State {
    STAY = "STAY",
    LEAVING = "LEAVING",
  }

  export const wedlockParse = (wedlock: Wedlock) => {
    switch (wedlock) {
      case Wedlock.MARRIED:
        return "已婚"
      case Wedlock.UNMARRIED:
        return "未婚"
      case Wedlock.DIVORCED:
        return "离异"
    }
  }

  export const degreeParse = (degree: Degree) => {
    switch (degree) {
      case Degree.DOCTOR:
        return "博士"
      case Degree.MASTER:
        return "硕士"
      case Degree.BEN:
        return "本科"
      case Degree.ZHUAN:
        return "专科"
      case Degree.HEIGHT:
        return "高中"
      case Degree.MIDDLE:
        return "初中"
      case Degree.LOW:
        return "小学"
      case Degree.OTHER:
        return "其他"
    }
  }

  export const stateParse = (state: State) => {
    switch (state) {
      case State.STAY:
        return "在职"
      case State.LEAVING:
        return "离职"
    }
  }
}
