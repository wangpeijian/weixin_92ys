/**
 * Created by peijian.wang on 2017/6/10.
 */
import React from 'react';

import '../../css/component/PageCountFooter.scss';

export default class PageCountFooter extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
		
		};
	}
	
	static defaultProps = {
		submitCallback : ()=>{console.log("submitCallback")},
		
		money: 0,
		invalid: 0,
		btnName: "",
	}
	
	//页面加载完毕
	componentDidMount() {
	
	}
	
	getDiscount(){
		let {money, invalid} = this.props;
		
		if(money === invalid || money === 0){
			return "";
		}
		
		money = Number(money).toFixed(2).toString().split(".");
		
		return <span className="invalid">
					{money[0]}
			<span className="decimal">.{money[1]}</span>
				</span>
	}
	
	getMoney(){
		let {invalid} = this.props;
		
		invalid = Number(invalid).toFixed(2).toString().split(".");
		
		return <span className="money">
						{invalid[0]}
			<span className="decimal">.{invalid[1]}</span>
					</span>
	}
	
	submit() {
		this.props.submitCallback();
	}
	
	render() {
		
		let {money, btnName} = this.props;
		
		return <div className="component-page-count-footer">
			<div className="footer">
				<label className="unit">RMB</label>
				
				{this.getMoney()}
				
				{this.getDiscount()}
				
				<span className={`buy ${money === 0 ? 'disable' : ''}`}
				      onClick={this.submit.bind(this)}>{btnName}</span>
			</div>
		</div>
	}
}