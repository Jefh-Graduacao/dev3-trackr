
import 'bootstrap/dist/css/bootstrap.css';
import "./assets/Tools.js";
import Vue from 'vue'
import App from './App.vue'
import VueResource from 'vue-resource'
Vue.use(VueResource);
Vue.config.productionTip = false

new Vue({
  render: h => h(App)
}).$mount('#app')
