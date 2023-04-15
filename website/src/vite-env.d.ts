/// <reference types="vite/client" />
declare interface Window {
  $router
}

interface ImportMetaEnv {
  readonly VITE_APP_BASE_URL: string
}
