import Vue from 'vue'
import Router from 'vue-router'

/*import Index from '../pages/Index'
import User from '../pages/User'
import Worker from '../pages/Worker'
import Worker_Edit from '../pages/Worker_Edit'
import Order from '../pages/Order'
import Article from '../pages/Article'
import Article_Edit from '../pages/Article_Edit'
import Product_Service from '../pages/Product_Service'
import Product_Resource from '../pages/Product_Resource'
import Product_Personal from '../pages/Product_Personal'
import Job_Type from '../pages/Job_Type'
import Resources_Type from '../pages/Resources_Type'
import Product_Edit from '../pages/Product_Edit'*/

const Index = resolve => require(['../pages/Index'], resolve);
const User = resolve => require(['../pages/User'], resolve);
const Worker = resolve => require(['../pages/Worker'], resolve);
const Worker_Edit = resolve => require(['../pages/Worker_Edit'], resolve);
const Order = resolve => require(['../pages/Order'], resolve);
const Article = resolve => require(['../pages/Article'], resolve);
const Article_Edit = resolve => require(['../pages/Article_Edit'], resolve);
const Product_Service = resolve => require(['../pages/Product_Service'], resolve);
const Product_Resource = resolve => require(['../pages/Product_Resource'], resolve);
const Product_Personal = resolve => require(['../pages/Product_Personal'], resolve);
const Job_Type = resolve => require(['../pages/Job_Type'], resolve);
const Resources_Type = resolve => require(['../pages/Resources_Type'], resolve);
const Product_Edit = resolve => require(['../pages/Product_Edit'], resolve);

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            component: Index
        },
        {
            path: '/user',
            component: User
        },
        {
            path: '/worker',
            component: Worker
        },
	    {
		    path: '/worker/edit',
		    component: Worker_Edit
	    },
        {
            path: '/order',
            component: Order
        },
        {
            path: '/article',
            component: Article
        },
	    {
		    path: '/article/edit',
		    component: Article_Edit
	    },
        {
            path: '/service',
            component: Product_Service
        },
        {
            path: '/resource',
            component: Product_Resource
        },
        {
            path: '/personal',
            component: Product_Personal
        },
	    {
		    path: '/job_type',
		    component: Job_Type
	    },
	    {
		    path: '/resources_type',
		    component: Resources_Type
	    },
        {
            path: '/product/edit',
            component: Product_Edit
        },
    ]
})
