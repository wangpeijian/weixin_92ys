/**
 * Created by peijian.wang on 2017/5/14.
 */
import React from 'react';
import Slider from 'react-slick';
import {Link} from 'react-router';
import createHistory from 'history/createHashHistory';
const history = createHistory();

import PageCountFooter from '../../component/PageCountFooter';


import '../../../css/product/Product.scss';

export default class Product extends React.Component {
	
	constructor(props) {
		super(props);
		
		let {pId} = this.props.location.query;
		
		this.state = {
			pId: pId,
			loading: false,
			
			//是否是私人项目
			isPrivate: false,
			isSingleMenu: false,
			
			
			money: 0,
			invalid: 0,
			
			userInfo: {
				name: "",
				head: "",
				duty: "",
				style: '',
			},
			
			article: {
				title: '',
				createTime: new Date(),
				pic: [""],
				content: "",
				discount: 1,
				haveAttachment: 0,
			},
			
			//预览图片的地址
			previewImageUrl: '',
			
			menuListData: [],
			
		};
		
		window.windowExtend.setPageTitle("92 | 因愫");
	}
	
	//页面加载完毕
	componentDidMount() {
		window.windowExtend.post("product/getDetail", {id: this.state.pId}).then(res => {
			let data = res.data;
			let productType = data.type;
			
			let isPrivate = false;
			let isSingleMenu = false;
			
			if(data.productItems.length === 1 && data.productItems[0].productItems.length === 1){
				isSingleMenu = true;
			}
			
			let userInfo = {};
			//判断是否是私人项目
			if(productType === 'personal'){
				isPrivate = true;
				
				let user = data.user;
				userInfo = {
					name: user.name,
					head: user.head,
					duty: user.jobType.name,
					style: user.style,
				}
			}else{
				userInfo = {
					name: windowExtend.getCompanyInfo().name,
					head: data.cover,
					duty: "",
					style: "",
				}
			}
			
			let article = {
				title: data.title,
				createTime: new Date(data.updateTime),
				pic: data.carousels.map(obj=>{
					return obj.url;
				}),
				content: data.content,
				discount: data.discount,
				haveAttachment: data.haveAttachment,
			};
			
			let menuListData = [];
			let money = 0;
			let invalid = 0;
			//如果是企业服务，则不展示收费项目。直接展示收费金额
			if(isSingleMenu){
				money = data.productItems[0].productItems[0].price;
				invalid = money * data.discount;
			}
			
			menuListData = data.productItems.map(ul => {
				return {
					name: ul.name,
					id: ul.id,
					no: ul.no,
					isItem: ul.isItem,
					item: ul.productItems.map(li => {
						return {
							name: li.name,
							price: li.price,
							num: 1,
							id: li.id,
							pid: li.pid,
							no: li.no,
							isItem: li.isItem
						}
					})
				}
			})
			
			this.setState({
				loading: false,
				isPrivate: isPrivate,
				isSingleMenu: isSingleMenu,
				
				article: article,
				menuListData: menuListData,
				
				money: money,
				invalid: invalid,
				
				userInfo: userInfo,
			}, ()=>{
				if(!isSingleMenu){
					this.countMoney();
				}
			})
		})
	}
	
	
	getHeader() {
		let content = "";
		
		if (this.state.isPrivate) {
			let {head, name, duty, style} = this.state.userInfo;
			
			let styleList = style.split(",").map((obj, i) => {
				return <li className="style-item" key={i}>{obj}</li>
			});
			
			content = <div className="user-info">
				<img className="head" src={head}/>
				<span className="name">{name}</span>
				<span className="duty">{duty}</span>
				<ul className="style">
					{styleList}
				</ul>
			</div>
			
		}else{
			let {title, createTime} = this.state.article;
			createTime = new Date(createTime).Format("yyyy-MM-dd hh:mm");
			
			content = <div className="article-info">
				<h2 className="article-title">{title}</h2>
				
				<div className="info-line">
					<span className="create-time">{createTime}</span>
				</div>
			</div>
		}
		
		return <div className="header">
			{content}
		</div>
	}
	
	getImages() {
		let data = this.state.article.pic;
		
		let array = [];
		
		for (let i in data) {
			let item = data[i];
			
			array.push(
				<img src={item} key={i} onClick={this.previewImage.bind(this, item)}/>
			)
		}
		
		return array;
	}
	
	previewImage(url) {
		if (!url) {
			url = "";
		}
		
		this.setState({
			previewImageUrl: url,
		});
	}
	
	getPreviewImage() {
		let previewImage = "";
		if (this.state.previewImageUrl) {
			let height = document.body.clientHeight;
			let width = document.body.clientWidth;
			
			let style = {
				backgroundImage: `url(${this.state.previewImageUrl})`,
				height: `${width}px`,
				width: `${height}px`,
				transformOrigin: `${width / 2}px`,
			};
			previewImage =
				<div onClick={this.previewImage.bind(this, "")} style={style} className="preview-image"></div>
		}
		
		return previewImage;
	}
	
	getBody() {
		return <div className="body">
			<Slider {...windowExtend.getCarouselConfig}>
				{this.getImages()}
			</Slider>
			
			<section className="content" dangerouslySetInnerHTML={{__html: this.state.article.content}}>
			</section>
		</div>
	}
	
	getMenu() {
		
		if(this.state.isSingleMenu){
			return "";
		}
		
		
		let menuList = this.state.menuListData.map((itemObj, pi) => {
			
			let {name, item} = itemObj;
			let sum = 0;
			
			let itemList = item.map((obj, si) => {
				
				let {name, price, num} = obj;
				
				//计算此菜单的合计
				sum += price * num;
				
				return <div className="item" key={si}>
					<span className="item-title">{name}</span>
					<span className="item-money"><span className="unit">￥</span>{price}</span>
					
					<div className="counter-box">
						<span className="icon-counter-sub" onClick={this.addItem.bind(this, pi, si, -1)}></span>
						<span className="counter-num">{num}</span>
						<span className="icon-counter-add" onClick={this.addItem.bind(this, pi, si, 1)}></span>
					</div>
				</div>
			});
			
			
			return <div className="list" key={pi}>
				<div className="menu-header">
					<span className="title">{name}</span>
					
					<span className="count-price">
						<span className="unit">RMB</span>
						<span className="money">{sum}</span>
					</span>
					
					<span className={`icon-menu-btn ${itemObj.isActive ? 'active' : ""}`}
					      onClick={this.changeItemActive.bind(this, pi, !itemObj.isActive)}></span>
				</div>
				
				<div className={`item-list ${itemObj.isActive ? 'active' : ""}`}>{itemList}</div>
			</div>
		});
		
		return <div className="menu">
			{menuList}
		</div>
	}
	
	addItem(pi, si, n) {
		let menuListData = this.state.menuListData;
		if (menuListData[pi].item[si].num === 0 && n < 0) {
			return;
		}
		
		menuListData[pi].item[si].num += n;
		
		this.setState({
			menuListData: menuListData,
		}, this.countMoney);
	}
	
	changeItemActive(pi, status) {
		let menuListData = this.state.menuListData;
		menuListData[pi].isActive = status;
		
		this.setState({
			menuListData: menuListData,
		});
	}
	
	countMoney() {
		let {
			money,
			invalid,
		} = this.state;
		
		money = 0;
		invalid = 0;
		
		let discount = this.state.article.discount;
		
		this.state.menuListData.map(obj => {
			obj.item.map(item => {
				money += item.num * item.price;
			});
		});
		
		if (discount) {
			invalid = money * discount;
		}
		
		this.setState({
			money: Number(money),
			invalid: Number(invalid),
		});
	}
	
	buyThis(){
		if(this.state.money === 0){
			return;
		}
		
		//将预订单配置信息保存到session中 isPrivate
		windowExtend.setSession(windowExtend.KEYS.PRODUCT_ORDER_INFO, {
			menuListData: this.state.menuListData,
			isPrivate: this.state.isPrivate,
			
			money: this.state.money,
			invalid: this.state.invalid,
			
			userInfo: this.state.userInfo,
			article: this.state.article,
			
			pId: this.state.pId
		});
		
		if(this.state.article.haveAttachment === 0){
			history.push("/sourceOrderConfirm");
		}else{
			history.push("/orderConfirm");
		}
		
	}
	
	render() {
		
		let {money, invalid} = this.state;
		
		return <div className="page-product">
			
			<div className="page-content">
				{/*此处有两种类型的头部，个人的和企业的*/}
				{this.getHeader()}
				
				{this.getBody()}
				
				{this.getMenu()}
				
				<PageCountFooter money={money} invalid={invalid} btnName="购买" submitCallback={this.buyThis.bind(this)} />
			</div>
			
			{this.getPreviewImage()}
		</div>
	}
}