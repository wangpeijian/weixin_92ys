/**
 * Created by admin on 2016/11/16.
 */

import React from 'react';
import {render} from 'react-dom';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';

/*安装polyfill*/
import "babel-polyfill";

//全局安装promise
import {install} from 'promise-es6'
install();

/*生成全局函数的方法*/
import WindowExtend from './public/WindowExtend';
window.windowExtend = new WindowExtend();

import "../css/Index.scss"

class App extends React.Component{

    constructor(props) {
        super(props);

        //TODO 授权成功后获取去用户信息
        // let {user_id} = this.props.location.query;
        //
        // fetch(windowExtend.API.getUrl("/wechat/info.do?user_id=" + user_id), {
        //     method: 'get',
        // }).then(function (response) {
        //     return response.json()
        // }).then(function(res){
        //     sessionStorage.setItem("userInfo", JSON.stringify(res));
        // })
    }
    
	render(){
		
		//TODO 隐藏头部菜单栏
		// windowExtend.API.callWeixin(
		// 	[
		// 		"hideOptionMenu",
		// 		"openLocation"
		// 	],
		//
		// 	function(){
		// 		wx.hideOptionMenu();
		// 	}
		// );
  
		return (<div>
			{this.props.children}
		</div>)
	}
}

import '../css/Index'

/*import ArticleList from './pages/article/ArticleList'
import Article from './pages/article/Article'

import RelationList from './pages/article/RelationList'

import ProductList from './pages/product/ProductList'
import WorkerClassifyList from './pages/product/WorkerClassifyList'
import Product from './pages/product/Product'

import Me from './pages/my/Me'

import About from './pages/info/About'
import Contract from './pages/info/Contract'*/


let ArticleList = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/article/ArticleList").default)
	},"ArticleList")
};
let Article = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/article/Article").default)
	},"Article")
};
let RelationList = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/article/RelationList").default)
	},"RelationList")
};

let ProductList = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/product/ProductList").default)
	},"ProductList")
};
let WorkerClassifyList = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/product/WorkerClassifyList").default)
	},"WorkerClassifyList")
};
let Product = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/product/Product").default)
	},"Product")
};

let Me = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/my/Me").default)
	},"Me")
};

let About = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/info/About").default)
	},"About")
};
let Contract = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/info/Contract").default)
	},"Contract")
};

let OrderConfirm = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/order/OrderConfirm").default)
	},"OrderConfirm")
};
let OrderList = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/order/OrderList").default)
	},"OrderList")
};
let SourceOrderConfirm = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/order/SourceOrderConfirm").default)
	},"SourceOrderConfirm")
};
let OrderDetail = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/order/OrderDetail").default)
	},"OrderDetail")
};
let SourceOrderDetail = (nextState,callback)=>{
	require.ensure([],(require)=>{
		callback(null,require("./pages/order/SourceOrderDetail").default)
	},"SourceOrderDetail")
};


try{
	render((
		<Router history={hashHistory}>
			<Route path="/" component={App}>
				<IndexRoute getComponent={ArticleList}/>
				
				<Route path="articleList" getComponent={ArticleList}/>
				<Route path="article" getComponent={Article}/>
				
				<Route path="relationList" getComponent={RelationList}/>
				
				<Route path="productList" getComponent={ProductList}/>
				<Route path="workerClassifyList" getComponent={WorkerClassifyList}/>
				<Route path="product" getComponent={Product}/>
				
				<Route path="me" getComponent={Me}/>
				
				<Route path="about" getComponent={About}/>
				<Route path="contract" getComponent={Contract}/>
				
				<Route path="orderConfirm" getComponent={OrderConfirm}/>
				<Route path="orderList" getComponent={OrderList}/>
				<Route path="sourceOrderConfirm" getComponent={SourceOrderConfirm}/>
				<Route path="orderDetail" getComponent={OrderDetail}/>
				<Route path="sourceOrderDetail" getComponent={SourceOrderDetail}/>
				
			</Route>
		</Router>
	),document.getElementById('react'));
}catch (e){
	alert(e);
}







