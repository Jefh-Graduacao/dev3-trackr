
import 'bootstrap/dist/css/bootstrap.css';
import "./assets/Tools.js";
import Vue from 'vue'
import App from './App.vue'
import VueResource from 'vue-resource'
import VueLoading from 'vue-loading-overlay'

Vue.use(VueResource);
Vue.use(VueLoading);
Vue.config.productionTip = false

new Vue({
  render: h => h(App)
}).$mount('#app')
