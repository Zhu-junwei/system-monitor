import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  // server可选配置
  server:{
    host: true, // 允许通过 IP 地址和 localhost 访问
  }
})
