// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import './plugins/bootstrap-vue'
import App from './App'
import router from './router'
import VueRouter from 'vue-router'
import BootstrapVue from 'bootstrap-vue'
import store from './Store/index1.js'
import Vuex from 'vuex'
Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.use(BootstrapVue)
Vue.use(store)
Vue.use(Vuex)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App }
})
