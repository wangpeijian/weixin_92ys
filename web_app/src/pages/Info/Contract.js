/**
 * Created by peijian.wang on 2017/5/14.
 */
import React from 'react';

import '../../../css/info/Contract.scss';

export default class Contract extends React.Component {
	
	constructor(props) {
		super(props);
		
		window.windowExtend.setPageTitle("92 | 协议");
	}
	
	//页面加载完毕
	componentDidMount() {
	
	}
	
	goBack(){
		history.go(-1);
	}
	
	
	render() {
		return <div className="page-contact">
			
			<div className="page-content">
			
				<p className="content">
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍
				</p>
				
				<p className="content">
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介
					绍公司介绍公司介绍公司介绍公司介绍
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介
					绍公司介绍公司介绍公司介绍公司介绍
				</p>
				
				<h2 className="part-title">授权许可与限制</h2>
				
				<h3 className="title">
					一、企业服务
				</h3>
				
				<p className="content">
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍
				</p>
				
				<h3 className="title">
					二、创意分享
				</h3>
				
				<p className="content">
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介
					绍公司介绍公司介绍公司介绍公司介绍
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介
					绍公司介绍公司介绍公司介绍公司介绍
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介
					绍公司介绍公司介绍公司介绍公司介绍
					公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介
					绍公司介绍公司介绍公司介绍公司介绍
				</p>
				
				<div className="confirm-btn" onClick={this.goBack}>确认</div>
			</div>
		
		</div>
	}
}