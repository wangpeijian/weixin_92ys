/**
 * Created by admin on 2016/11/16.
 */
import React from 'react';

import createHistory from 'history/createHashHistory';
const history = createHistory();

import '../../css/component/Navigation';

/**
 * 页面底部导航栏
 *
 * 2016-11-17
 * 王佩剑
 */
export default class Navigation extends React.Component{
	constructor(props){
		super(props);

		this.state = {
			active: props.active,
		}
	}

	static defaultProps = {
		active: 0,

		navigationData: [
			{
				name : "因愫",
				pathName: "articleList",
			},
			{
				name : "创意",
				pathName: "productList",
			},
			{
				name : "我的",
				pathName: "me",
			}
		]
	};
	
	componentWillReceiveProps(nextProps){
		
		if(nextProps.active == this.state.active){
			return;
		}
		
		this.setState({
			active: nextProps.active,
		});
	}

	//生底部导航按钮
	getButtons(){
		let data = this.props.navigationData;

		let array = [];

		for(let i in data){
			let item = data[i];

			let className = "item";
			if(i == this.state.active){
				className = "item active"
			}

			array.push(
				<div className={className} key={i} onClick={this.changePage.bind(this, i, item.pathName)}>
					<span className={"nav-icon"}></span>
					<span className="nav-name">{item.name}</span>
				</div>
			)
		}
		return array;
	}
	
	//页面跳转
	changePage(index, pathName){
		if(index == this.state.active){
			return false;
		}
		
		history.replace(pathName)
	}
	
	
	stopDefault(e){
		e.preventDefault();
		e.stopPropagation();
		return false;
	}

	render(){

		return <div className="component-navigation" onTouchMove={this.stopDefault.bind(this)}>
			<div className="navigation-bar">
				{this.getButtons()}
			</div>
		</div>
	}
}