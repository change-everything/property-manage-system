/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import echarts from 'echarts'
import store from './store/index'

import "./mock/owner.js"
import "./mock/employee.js"
import "./mock/unit.js"
import "./mock/fees.js"

import * as ElementPlusIconsVue from '@element-plus/icons-vue'


// 创建app
const app = createApp(App)

// 注入路由
app.use(router)
app.use(ElementPlus,{locale:zhCn})
app.use(echarts)
app.use(store)

//引入图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

app.mount('#app')
