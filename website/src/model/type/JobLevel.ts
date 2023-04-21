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

export namespace JobLevel {
  export enum Level {
    HEIGHT = "HEIGHT",
    PRE_HEIGHT = "PRE_HEIGHT",
    MIDDLE = "MIDDLE",
    LOW = "LOW",
    NOOB = "NOOB",
  }

  export const levelParse = (level: Level) => {
    switch (level) {
      case JobLevel.Level.HEIGHT:
        return "正高级"
      case JobLevel.Level.PRE_HEIGHT:
        return "副高级"
      case JobLevel.Level.MIDDLE:
        return "中级"
      case JobLevel.Level.LOW:
        return "初级"
      case JobLevel.Level.NOOB:
        return "员级"
    }
  }
}
