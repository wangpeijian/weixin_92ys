/**
 * Created by peijian.wang on 2017/6/3.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';

import '../../../css/product/Product.scss';
import '../../../css/order/OrderConfirm.scss';
import '../../../css/order/SourceOrderConfirm.scss';

import PageCountFooter from '../../component/PageCountFooter';

export default class SourceOrderConfirm extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			loading: false,
			
			money: 0,
			invalid: 0,
			
			discount: 0.9,
			title: '',
			img: '',
			author: '',
		};
		
		window.windowExtend.setPageTitle("92 | 核对订单");
	}
	
	//页面加载完毕
	componentDidMount() {
		let {article, money, invalid, userInfo, pId, menuListData} = window.windowExtend.getSession(windowExtend.KEYS.PRODUCT_ORDER_INFO);
		
		this.setState({
			money: money,
			invalid: invalid,
			
			discount: article.discount,
			title: article.title,
			img: userInfo.head,
			author: userInfo.name,
			
			menuListData: menuListData,
			
			pId: pId
		});
	}
	
	buyThis(){
		let userInfo = window.windowExtend.getSession(windowExtend.KEYS.USER_INFO);
		
		window.windowExtend.post("order/addOrder", {
			customerId: userInfo.id,
			productId: this.state.pId,
			productItemSnapshots: this.state.menuListData.map(ul => {
				ul.productItemSnapshots = ul.item;
				return ul;
			}),
			price: this.state.invalid,
			remark: this.refs.remark.value.trim(),
		}).then(res => {
		
		});
	}
	
	render() {
		let {money, invalid, title, author, img, discount, remark} = this.state;
		
		return <div className="page-order-confirm page-source-order-confirm">
			
			<div className="page-content">
				
				<div className="order-header">
					<div className="info-box">
						<p className="title">{title}</p>
						<p className="author">{author}</p>
					</div>
					
					<img className="cover" src={img}/>
				</div>
				
				<div className="order-body">
					<p className="item-count">
						<span className="label">单价</span>
						<span className="data">{money}</span>
					</p>
					
					<p className="item-count">
						<span className="label">折扣</span>
						<span className="data">{discount === 1 ? "-" : `${discount * 10}折`}</span>
					</p>
					
					<p className="item-count">
						<span className="label">总价</span>
						<span className="data">{money * discount}</span>
					</p>
					
					
				</div>
				
				
				<div className="confirm-form">
					<textarea type="text" className="confirm-input confirm-textarea" placeholder="备注" ref="remark"></textarea>
					
					<p className="service bold">92 | 客服</p>
					<p className="service">{window.windowExtend.getCompanyInfo().phoneLabel}</p>
				</div>
				
				
				<PageCountFooter money={money} invalid={invalid} btnName="支付" submitCallback={this.buyThis.bind(this)} />
			</div>
			
			<Loading loading={this.state.loading}/>
		</div>
	}
}