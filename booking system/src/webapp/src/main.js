import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

// import "bootstrap-vue/dist/bootstrap-vue.css"
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.common['token'] = localStorage['token']
Object.defineProperty(Vue.prototype, '$axios', {value: axios});

import VueToastr$1 from 'vue-toastr'
// import "toastr/build/toastr.css"
// Object.defineProperty(Vue.prototype, '$toastr', {value: VueToastr$1})
Vue.use(VueToastr$1, {defaultPosition: 'toast-top-center', defaultTimeout: 3000})
router.beforeEach((to, from, next) => {
  if (to.meta.title !== '')
    document.title = to.meta.title;
  next()
})


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
