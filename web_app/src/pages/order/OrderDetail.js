/**
 * Created by peijian.wang on 2017/6/3.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';

import '../../../css/order/OrderDetail.scss';

import PageCountFooter from '../../component/PageCountFooter';

export default class OrderDetail extends React.Component {
	
	constructor(props) {
		super(props);
		
		let {id} = this.props.location.query;
		
		this.state = {
			loading: true,
			id: id,
			
			author: {
				img: "",
				name: "",
				duty: "",
				phone: "",
			},
			
			orderData: {
				status: "",
				id: "",
				time: "",
				remark: "",
				money: 0,
				invalid: 0,
				discount: 1,
				director: "",
				directorPhone: "",
			},
			menuListData: [],
		};
		
		window.windowExtend.setPageTitle("92 | 订单详情");
	}
	
	//页面加载完毕
	componentDidMount() {
		let userInfo = window.windowExtend.getSession(windowExtend.KEYS.USER_INFO);
		
		window.windowExtend.post("order/getOrderDetail", {
			customerId: userInfo.id,
			id: this.state.id,
		}).then(res => {
			let data = res.data;
			
			let author = {};
			
			//判断是否是私人项目
			if(data.authorId !== ''){
				let user = data.author;
				author = {
					img: user.head,
					name: user.name,
					duty: user.jobType.name,
					phone: user.phone,
				}
			}else{
				let {name, phone} = window.windowExtend.getCompanyInfo();
				author = {
					name: name,
					img: data.cover,
					duty: "-",
					phone: phone,
				}
			}
			
			let orderData = {
				status: data.status === 1 ? "已完成" : "进行中",
				id: data.id,
				time: new Date(data.date).Format("yyyy-MM-dd hh:mm"),
				remark: data.remark,
				money: data.discount === 1 ? 0 : data.price / data.discount,
				invalid: data.price,
				discount: data.discount,
				director: data.director,
				directorPhone: data.directorPhone,
			};
			
			let menuListData = data.productItemSnapshots.map(obj => {
				return {
					name: obj.name,
					item: obj.productItemSnapshots
				}
			});
			
			this.setState({
				loading: false,
				author: author,
				orderData: orderData,
				menuListData: menuListData,
			});
		});
	}
	
	getHeader(){
		let {img, name, duty} = this.state.author;
		
		return <div className="header">
			<img className="head" src={img}/>
			<p className="name">{name}</p>
			<p className="duty">{duty}</p>
			
			<Link to={{pathname: "/contract"}}>
				<div className="contract-btn">查看合同</div>
			</Link>
		</div>
	}
	
	getMenu() {
		let menuList = this.state.menuListData.map((itemObj, pi) => {
			
			let {name, item} = itemObj;
			
			let itemList = item.map((obj, si) => {
				
				let {name, price, num} = obj;
				
				return <div className="item" key={si}>
					<span className="item-title">{name}</span>
					<span className="item-money"><span className="unit">￥</span>{price}</span>
					<span className="counter-num">{num}</span>
				</div>
			});
			
			
			return <div className="list" key={pi}>
				<div className="menu-header">
					<span className="title">{name}</span>
				</div>
				
				<div className="item-list">{itemList}</div>
			</div>
		});
		
		return <div className="menu">
			{menuList}
		</div>
	}
	
	getOrderInfo(){
		
		let {id, status, time, remark, discount, director, directorPhone} = this.state.orderData;
		
		return <div className="info-group">
			<div className="info-line">
				<span className="label">折扣</span>
				<span className="info">{discount === 1 ? "-" : discount}</span>
			</div>
			<div className="info-line">
				<span className="label">订单状态</span>
				<span className="info">{status}</span>
			</div>
			<div className="info-line">
				<span className="label">订单编号</span>
				<span className="info">{id}</span>
			</div>
			<div className="info-line">
				<span className="label">下单日期</span>
				<span className="info">{time}</span>
			</div>
			
			<div className="info-line">
				<span className="label">负责人</span>
				<p className="key-point">{director}</p>
			</div>
			<div className="info-line">
				<span className="label">负责人电话</span>
				<p className="key-point">
					<a href={`tel:${directorPhone}`}>
						<span>Phone:{directorPhone}</span>
						<span className="btn">拨打电话</span>
					</a>
				</p>
			</div>
			
			
			<div className="remark">{remark}</div>
			
			<p className="service bold">92 | 客服</p>
			<p className="service">{window.windowExtend.getCompanyInfo().phoneLabel}</p>
		</div>
		
	}
	
	buyThis(){
	
	}
	
	render() {
		
		let {money, invalid} = this.state.orderData;
		
		return <div className="page-order-detail">
			
			<div className="page-content">
				{this.getHeader()}
				
				{this.getMenu()}
				
				{this.getOrderInfo()}
				
				<PageCountFooter money={money} invalid={invalid} btnName="再次购买" submitCallback={this.buyThis.bind(this)} />
			</div>
			
			<Loading loading={this.state.loading}/>
		</div>
	}
}