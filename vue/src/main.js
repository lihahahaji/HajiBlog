// 1
import ElementPlus from 'element-plus'
// 2
import 'element-plus/dist/index.css'

import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
import createKatexPlugin from '@kangc/v-md-editor/lib/plugins/katex/cdn';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';

import { createApp } from 'vue'

import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue'
import main from './components/main.vue'
import edit from './components/edit.vue'
// highlightjs
import hljs from 'highlight.js';

// 1 添加路由配置
const router = createRouter({
  history: createWebHistory(),
  routes: [
      {
          path: '/main',
          name: 'main',
          component: main
      },
      {
          path: '/edit',
          name: 'edit',
          component: edit
      },
      {
          path: '/',
          redirect: '/main'
      }
  ]
});


VueMarkdownEditor.use(githubTheme,{
    Hljs: hljs,
  })
VueMarkdownEditor.use(createCopyCodePlugin())
VueMarkdownEditor.use(createKatexPlugin());

const app = createApp(App)
// 3
app.use(ElementPlus)
app.use(VueMarkdownEditor)
app.use(router);

app.mount('#app')
