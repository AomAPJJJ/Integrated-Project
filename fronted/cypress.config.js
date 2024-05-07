import { defineConfig } from "cypress"

export default defineConfig({
  e2e: {
    specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
    //baseUrl: "http://localhost:5173",
    baseUrl: "http://ip23sj3.sit.kmutt.ac.th",
  },
  component: {
    specPattern: "src/**/__tests__/*.{cy,spec}.{js,ts,jsx,tsx}",
    devServer: {
      framework: "vue",
      bundler: "vite",
    },
  },
})