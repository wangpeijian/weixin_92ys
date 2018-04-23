/**
 * Created by peijian.wang on 2017/5/14.
 */
import React from 'react';
import {Link} from 'react-router';

import '../../../css/info/About.scss';

export default class About extends React.Component {
	
	constructor(props) {
		super(props);
		
		window.windowExtend.setPageTitle("关于我们");
	}
	
	//页面加载完毕
	componentDidMount() {
	
	}
	
	
	render() {
		return <div className="page-about">
			
			<div className="page-content">
				<div className="header">
					<span className="logo"></span>
					<h2 className="company-name">北京九二因愫文化创意有限公司</h2>
					<p className="slogan">因九二，因事制宜，因吾成事</p>
				</div>
			
				<div className="body">
					
					<h3 className="title">
						公司介绍
					</h3>
					<p className="content">
						公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍
					</p>
					<p className="content">
						公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍公司介绍
					</p>
					
					<h3 className="title">
						业务合作
					</h3>
					<p className="content">
						请发送邮件至 <a href="mailto:epigt@icloud.com">epigt@icloud.com</a>
					</p>
					
					<h3 className="title">
						投诉建议
					</h3>
					<p className="content">
						请发送邮件至 <a href="mailto:epigt@icloud.com">epigt@icloud.com</a>
					</p>
					
					<h3 className="title">
						客服电话
					</h3>
					<p className="content">
						PHONE <a href="tel:15001085992">150-010-85992</a>
					</p>
					
					<Link to={{pathname: '/contract'}}>
						<h3 className="title link">
							查看九二协议
						</h3>
					</Link>
					
				</div>
			
			</div>
			
		
		</div>
	}
}