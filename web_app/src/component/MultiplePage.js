/**
 * Created by admin on 2016/11/19.
 */
import React from 'react';

import '../../css/component/MultiplePage.scss';

/**
 * 左右滑动的组件
 *
 * 嵌套在组件内的子元素会左右滚动
 *
 * 2016-11-07
 * 王佩剑
 *
 */
export default class MultiplePage extends React.Component{
	constructor(props){
		super(props);

		this.state = {
			current: props.current,
		}

		global.scrollStartX = 0;

		global.scrollMarkX = 0;
		global.scrollMarkY = 0;

		global.windowWidth = document.body.clientWidth;
	}

	static defaultProps = {
		slideCallback : (current)=>{console.log(current)},

		pages: [1,2,3,4],
		
		current: 0,
	}
	
	componentDidMount(){
		this._resizePage();
	}
	
	componentDidUpdate(){
	    
	}
	
	componentWillReceiveProps(nextProps){
		
		if(nextProps.current == this.state.current){
			return;
		}
		
		this.setState({
			current: nextProps.current,
		}, ()=>{
			let list = this.refs.scrollBlock;
			this._addAnimationClass(list);
			list.style.left = (global.windowWidth * - nextProps.current)  + "px";
			
			this._resizePage();
		})
	}
	
	//用户手指滑动
	touchStart(e){
		global.scrollStartX = e.targetTouches[0].screenX;

		global.scrollMarkX = e.targetTouches[0].screenX;
		global.scrollMarkY = e.targetTouches[0].screenY;
		
		global.isLeft = false;

		return e.stopPropagation();
	}

	touchMove(e){
		//纵向滑动大于横向滑动时，不阻止浏览器默认行为，触发浏览器滚动条滑动效果
		if(Math.abs(e.targetTouches[0].screenY - global.scrollMarkY) < Math.abs(e.targetTouches[0].screenX - global.scrollMarkX)){
			e.preventDefault();

			let list = this.refs.scrollBlock;//e.currentTarget.children[0];
			let left = list.style.left.replace(/px/g, "");

			let styleLeft = (~~left - ~~(global.scrollStartX - e.targetTouches[0].screenX));
			
			if(styleLeft > 0 || -styleLeft > global.windowWidth *　(this.props.pages.length - 1)){
				
			}else{
				list.style.left = styleLeft + "px";
			}
			
			//标记当前是否是向左侧滑动
			if(global.scrollStartX - e.targetTouches[0].screenX > 0){
				global.isLeft = true;
			}else if(global.scrollStartX - e.targetTouches[0].screenX < 0){
				global.isLeft = false;
			}
			
			global.scrollStartX = e.targetTouches[0].screenX;
		}
		
		return e.stopPropagation();
	}

	touchEnd(e){
		
		global.scrollStartX = 0;

		let list = this.refs.scrollBlock;//e.currentTarget.children[0];
		let left = list.style.left.replace(/px/g, "");

		let maxIndex = this.props.pages.length - 1;

		let current = 0
		
		//滑动30%则可以触发翻页
		if(global.isLeft){
			current = parseInt((Math.abs(left) / global.windowWidth) + 0.7)
		}else{
			current = parseInt((Math.abs(left) / global.windowWidth) + 0.3)
		}

		this._addAnimationClass(list);

		if(left >= 0){
			list.style.left = "0px";
			current = 0;
		}else if(current > maxIndex){
			list.style.left = (global.windowWidth * - maxIndex)  + "px";
			current = maxIndex;
		}else {
			list.style.left = (global.windowWidth * - current)  + "px";
		}

		if(current !== this.state.current){
			this.setState({
				current: current,
			})
			
			this.props.slideCallback(current);
		}
	}
	
	//滚动前为滚动部分添加动画效果
	_addAnimationClass(list){
		list.className = "slide-block scrolling";
		setTimeout(function(){
			list.className = "slide-block";
		}, 300);
	}
	
	//开启autoHeight后，将控件高度设置为当前页面的高度
	_resizePage(){
		let list = this.refs.scrollBlock;
		let node = this.refs["node_" + this.state.current];
		
		let height = node.offsetHeight;
		if(node.offsetHeight < document.body.clientHeight){
			height = document.body.clientHeight;
		}
		
		list.style.height = height + "px";
		
	}

	//根据传输的页面数据，生成滚动的页面列表
	createPages() {
		let pages = this.props.pages;
		let array = [];
		let width = (100 / pages.length) + "%";
		
		pages.forEach(function(item, index){
			array.push(
				<li className="item-block"
				    key={index}
				    style={{width: width}}
				    ref={"node_" + index}
				>
					{item}
				</li>
			)
		})

		return array;
	}


	render(){
		return <div className="component-multiple-page">
			<div className="scroll-block" onTouchStart={this.touchStart.bind(this)}
			     onTouchMove={this.touchMove.bind(this)}
			     onTouchEnd={this.touchEnd.bind(this)}>

				<ul className="slide-block" ref="scrollBlock" style={{left: "0px", width: (100 * this.props.pages.length) + "%"}}>

					{this.createPages()}

				</ul>

			</div>
		</div>
	}
}