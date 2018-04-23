/**
 * Created by peijian.wang on 2017/6/5.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';

import '../../../css/order/OrderDetail.scss';
import '../../../css/order/SourceOrderDetail.scss';

import PageCountFooter from '../../component/PageCountFooter';


export default class SourceOrderDetail extends React.Component {
	
	constructor(props) {
		super(props);
		
		let {id} = this.props.location.query;
		
		this.state = {
			loading: true,
			id: id,
			
			author: {
				img: "",
				name: "",
				title: "",
			},
			orderData: {
				status: "",
				id: "",
				time: "",
				remark: "",
				money: 0,
				invalid: 0,
				discount: 1,
				attachmentURL: "",
				attachmentPassword: "",
			},
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
			
			let author = {
				img: data.cover,
				title: data.title
			};
			
			//判断是否是私人项目
			if(data.authorId !== ''){
				let user = data.user;
				author.name = user.name;
			}else{
				let {name} = window.windowExtend.getCompanyInfo();
				author.name = name;
			}
			
			if((new Date().getTime() - new Date(data.date).getTime()) / (24 * 3600 * 1000) > 3){
				data.attachmentURL = "";
				data.attachmentPassword = "";
			}
			
			let orderData = {
				status: data.status === 1 ? "已完成" : "进行中",
				id: data.id,
				time: new Date(data.date).Format("yyyy-MM-dd hh:mm"),
				remark: data.remark,
				money: data.discount === 1 ? 0 : data.price / data.discount,
				invalid: data.price,
				discount: data.discount,
				attachmentURL: data.attachmentURL,
				attachmentPassword: data.attachmentPassword,
			};
			
			
			this.setState({
				loading: false,
				author: author,
				orderData: orderData,
			});
		});
	}
	
	getHeader(){
		
		let {title, name, img} = this.state.author;
		
		return <div className="source-info">
			<p className="title">{title}</p>
			<p className="author">作者 {name}</p>
			
			<Link to={{pathname: "/contract"}}>
				<p className="contract-btn">查看九二协议</p>
			</Link>
			
			<img className="cover" src={img}/>
		</div>
	
	}
	
	getOrderInfo(){
		let {id, status, time, remark, attachmentURL, attachmentPassword, discount} = this.state.orderData;
		
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
				<span className="label">网盘地址</span>
				<p className="key-point">{attachmentURL}</p>
			</div>
			<div className="info-line">
				<span className="label">网盘密码 <span className="tip">(此密码有效期3天)</span> </span>
				<p className="key-point">{attachmentPassword}</p>
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
		
		return <div className="page-order-detail page-source-order-detail">
			
			<div className="page-content">
				{this.getHeader()}
				
				{this.getOrderInfo()}
			</div>
			
			<PageCountFooter money={money} invalid={invalid} btnName="再次购买" submitCallback={this.buyThis.bind(this)} />
			
			<Loading loading={this.state.loading}/>
		</div>
	}
}