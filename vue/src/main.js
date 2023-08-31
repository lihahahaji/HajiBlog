// 1
import ElementPlus from 'element-plus'
// 2
import 'element-plus/dist/index.css'

import { createApp } from 'vue'
import App from './App.vue'


const app = createApp(App)
// 3
app.use(ElementPlus)

app.mount('#app')
