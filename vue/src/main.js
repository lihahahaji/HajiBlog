// 1
import ElementPlus from 'element-plus'
// 2
import 'element-plus/dist/index.css'

import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';


import { createApp } from 'vue'
import App from './App.vue'

VueMarkdownEditor.use(vuepressTheme)
VueMarkdownEditor.use(createCopyCodePlugin)
const app = createApp(App)
// 3
app.use(ElementPlus)
app.use(VueMarkdownEditor)

app.mount('#app')
