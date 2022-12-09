import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/global.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

const app = createApp(App)
// app.use(ElementPlus)
app.use(ElementPlus, {
    locale: zhCn
})
app.use(store)
app.use(router)

app.mount('#app')
