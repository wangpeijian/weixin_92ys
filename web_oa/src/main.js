// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import App from './App'
import router from './router'
Vue.config.productionTip = false;

/*---------------------------------------------------*/
//引入element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
Vue.use(ElementUI);

//判断是否是生产环境，生产环境下，将console方法重写
if(process.env.NODE_ENV === 'production'){
    window.console.log = ()=>{};
    window.console.info = ()=>{};
    window.console.error = ()=>{};
}

//全局安装promise
import {install} from 'promise-es6'
install();

import 'whatwg-fetch'

import plug from './public/plug'
Vue.use(new plug());
/*---------------------------------------------------*/

/* eslint-disable no-new */
new Vue({
	el: '#app',
	router,
	template: '<App/>',
	components: {App}
})
