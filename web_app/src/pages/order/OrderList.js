/**
 * Created by peijian.wang on 2017/6/3.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';
import Dialog from '../../component/Dialog';

import '../../../css/order/OrderList.scss';


export default class OrderList extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			loading: true,
			
			orderData: [],
			
			closeOrderId: "",
		};
		
		window.windowExtend.setPageTitle("92 | 我的订单");
	}
	
	//页面加载完毕
	componentDidMount() {
		this.getOrderList();
	}
	
	getOrderList(){
		let userInfo = windowExtend.getSession(windowExtend.KEYS.USER_INFO);
		window.windowExtend.post("order/getUserOrder", {
			id: userInfo.id
		}).then(res => {
			this.setState({
				loading: false,
				
				orderData: res.data.map(item => {
					return {
						id: item.id,
						title: item.title,
						statusName: item.status === 0 ? "进行中" : "已完工",
						statusCode: item.status,
						time: new Date(item.date).Format("yyyy-MM-dd hh:mm"),
						count: item.price,
						type: item.type,
						img: item.cover,
						haveAttachment: item.haveAttachment,
					}
				})
			})
		});
	}
	
	getOrderListHTML(){
		return this.state.orderData.map((obj, i) => {
			
			let {id, title, statusName, statusCode, time, count, type, img, haveAttachment} = obj;
			
			
			return <Link to={{pathname: haveAttachment === 1 ? '/sourceOrderDetail' : '/orderDetail', query: {id: id}}} key={i}>
				<div className="order-panel">
					<div className="top">
						<p className="first-line">
							<span className="title">{title}</span>
							<span className="status">{statusName}</span>
						</p>
						
						<div className="info-box">
							<img className="cover" src={img} />
							
							<p className="order-no">{id}</p>
							<p className="time">{time}</p>
						</div>
						
					</div>
					
					<div className="bottom">
						<p className="count-label">总计</p>
						<p className="count">
							<span className="unit">RMB</span>
							<span className="price">{count}</span>
						</p>
						
						{
							statusCode === 0 ? <div className="button" onClick={(e)=>{
								this.setState({
									closeOrderId: id,
								});
								
								e.preventDefault();
								e.stopPropagation();
								return false;
							}}>确认完工</div> : ""
						}
					</div>
				</div>
			</Link>
		});
	}
	
	//关闭订单
	closeOrder(){
		let userInfo = windowExtend.getSession(windowExtend.KEYS.USER_INFO);
		window.windowExtend.post("order/closeOrder", {
			id: this.state.closeOrderId,
			customerId: userInfo.id,
		}).then(res => {
			this.setState({
				closeOrderId: "",
				loading: true,
			}, this.getOrderList);
		});
	}
	
	render() {
		return <div className="page-order-list">
			
			{this.getOrderListHTML()}
			
			<Dialog textTitle = "提示"
					ok = {this.closeOrder.bind(this)}
					textOk = "确认"
					cancel = {()=>{
						this.setState({
							closeOrderId: "",
						})
					}}
					textCancel = "取消"
					textTip = "确认关闭此订单吗？"
					visible = {this.state.closeOrderId !== ""}
			/>
			<Loading loading={this.state.loading}/>
		</div>
	}
}