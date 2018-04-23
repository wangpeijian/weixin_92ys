/**
 * Created by peijian.wang on 2017/6/3.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';

import '../../../css/product/Product.scss';
import '../../../css/order/OrderConfirm.scss';

import PageCountFooter from '../../component/PageCountFooter';

export default class OrderConfirm extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			loading: false,
			
			menuListData: [],
			
			article: {
				discount: 1,
			},
			
			money: 0,
			invalid: 0,
		};
		
		window.windowExtend.setPageTitle("92 | 核对订单");
	}
	
	//页面加载完毕
	componentDidMount() {
		
		let {menuListData, article, money, invalid, pId} = window.windowExtend.getSession(windowExtend.KEYS.PRODUCT_ORDER_INFO);
		
		this.setState({
			menuListData: menuListData,
			article: article,
			money: money,
			invalid: invalid,
			
			pId: pId
		});
	}
	
	
	getConfirmList() {
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
			
			let discount = this.state.article.discount;
			if(discount){
				discount = (sum *  (1 - discount)).toFixed(2);
			}else{
				discount = "";
			}
			
			return <div className="list" key={pi}>
				<div className="menu-header">
					<span className="title">{name}</span>
				</div>
				
				<div className="item-list active">{itemList}</div>
				
				<p className="item-count">
					<span className="label">折扣</span>
					<span className="data">-{discount}</span>
				</p>
				<p className="item-count">
					<span className="label">总价</span>
					<span className="data">{sum.toFixed(2)}</span>
				</p>
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
		let {money, invalid} = this.state;
		
		return <div className="page-product page-order-confirm">
			
			<div className="page-content">
				{this.getConfirmList()}
				
				
				<div className="confirm-form">
					<p className="tip">以下信息用于生成合同，请认真填写</p>
					
					<input type="text" className="confirm-input" placeholder="请填写本人姓名或公司名称"/>
					
					<input type="text" className="confirm-input" placeholder="请填写人份证号或营业执照号码"/>
					
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