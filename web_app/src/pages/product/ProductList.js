/**
 * Created by peijian.wang on 2017/4/23.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';
import Navigation from '../../component/Navigation';

import '../../../css/product/ProductList.scss';

export default class ProductList extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			loading: true,
			
			service: [],
			
			resource: [],
			
			personal: []
		};
		
		window.windowExtend.setPageTitle("创意");
	}
	
	//页面加载完毕
	componentDidMount() {
		window.windowExtend.post("product/getHomeData", {id: this.state.aId}).then(res=>{
			
			let resource = res.data.resource.map(obj => {
				obj.title = obj.name;
				return obj;
			});
			
			this.setState({
				service: res.data.service,
				resource: resource,
				personal: [{title: "全部"}].concat(resource),
				loading: false,
			})
		})
	}
	
	getList(){
		
		let array = [];
		
		let type = [{
			list: 'service',
			title: '企业服务',
			desc: '| 公众号运营 | 网站搭建 | 平面设计 |',
		},{
			list: 'resource',
			title: '创意分享',
			desc: '有什么分享什么而已',
		},{
			list: 'personal',
			title: '私人分享',
			desc: '私人产品 | 设计分享',
		}];
		
		type.map((item, index) => {
			let list = this.state[item.list];
			
			let group = [];
			
			list.map((obj, order) => {
				
				let bgStyle = {
					backgroundImage: `url('${obj.cover}')`
				};
				
				if (item.list === "personal" && order === 0) {
					bgStyle = {
						background: `#333`
					};
				}
				
				
				let urlInfo = {};
				
				switch (item.list){
					case "service":
						
						urlInfo = {
							pathname: '/product',
							query: {pId: obj.id}
						};
						
						break;
					case "resource":
						
						urlInfo = {
							pathname: '/relationList',
							query: {type: "resource", tId: obj.id}
						};
						
						break;
					case "personal":
						
						if(order > 0){
							
							urlInfo = {
								pathname: '/relationList',
								query: {type: "personal", tId: obj.id}
							};
							
						}else{
							
							urlInfo = {
								pathname: '/workerClassifyList',
								query: {}
							};
							
						}
						break;
				}
				
				
				group.push(
					<Link to={urlInfo} key={`${index}-${order}`}>
						<div className="cover" style={bgStyle}>
							<p className="label">{obj.title}</p>
						</div>
					</Link>
				)
			});
			
			array.push(
				<div key={index}>
					<div className="header">
						<span className="type-logo"> </span>
						
						<p className="title">
							{item.title}
						</p>
						<p className="desc">
							{item.desc}
						</p>
					</div>
					
					<div className="cover-list">
						{group}
					</div>
				</div>
			)
		});
		
		
		return array;
	}
	
	
	render() {
		return <div className="page-product-list">
			
			{this.getList()}
			
			<Navigation active={1}/>
			<Loading loading={this.state.loading}/>
		</div>
	}
}