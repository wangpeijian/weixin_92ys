/**
 * Created by peijian.wang on 2017/4/20.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';
import Navigation from '../../component/Navigation';

import '../../../css/my/me.scss';


export default class Me extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			loading: false,
			
			name: "王配件爱你",
			head: "http://wx4.sinaimg.cn/mw690/66134906ly1fddlgaicu2j21sg2dse86.jpg",
		};
		
		window.windowExtend.setPageTitle("我的");
	}
	
	//页面加载完毕
	componentDidMount() {
	
	}
	
	
	render() {
		return <div className="page-me">
			
			<div className="page-content">
			
				<div className="user-info">
					<img src={this.state.head} className="head"/>
					<span className="name">{this.state.name}</span>
				</div>
			
				<ul className="menu">
					
					<Link to={{pathname: '/orderList'}}>
						<li>我的订单</li>
					</Link>
					
				{/*	<li>我的收藏</li>
					
					<li>个人资料</li>*/}
					
					<Link to={{pathname: '/about'}}>
						<li>关于我们</li>
					</Link>
				</ul>
			
			</div>
			
			<Navigation active={2}/>
			<Loading loading={this.state.loading}/>
		</div>
	}
}