/**
 * Created by peijian.wang on 2017/4/20.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';
import Navigation from '../../component/Navigation';


import '../../../css/article/ArticleList.scss';

import logo from '../../../img/logo.png';

export default class ArticleList extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			loading: true,
			
			list: []
		};
		
		window.windowExtend.setPageTitle("因愫");
	}
	
	//页面加载完毕
	componentDidMount() {
		window.windowExtend.post("article/getCoverList", {}).then(res=>{
			this.setState({
				list: res.data,
				loading: false,
			})
		})
	}
	
	getArticleList() {
		let data = this.state.list;
		let array = [];
		
		data.map((item, index) => {
			
			let imgStyle = {
				backgroundImage: `url("${item.cover}")`,
			};
			
			array.push(
				<Link to={{pathname: "/article", query:{aId: item.id,}}} key={index}>
					<li className="item" style={imgStyle}>
						<p className="title">{item.title}</p>
					</li>
				</Link>
			)
		});
		
		return array;
	}
	
	render() {
		return <div className="page-article-list">
			
			<div className="page-title">
				<img className="logo" src={logo} />
			</div>
			
			<ul className="list">
				{this.getArticleList()}
			</ul>
			
			
			<Navigation active={0}/>
			<Loading loading={this.state.loading}/>
		</div>
	}
}