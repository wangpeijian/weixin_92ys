/**
 * Created by peijian.wang on 2017/4/30.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';

import '../../../css/article/RelationList.scss';

export default class RelationList extends React.Component {
	
	constructor(props) {
		super(props);
		
		let {aId, type, tId} = this.props.location.query;
		
		this.state = {
			loading: true,
			
			aId: aId,
			type: type,
			tId: tId,
			
			list: []
		};
		
		window.windowExtend.setPageTitle("92 | 因愫");
	}
	
	//页面加载完毕
	componentDidMount() {
		let {aId, type, tId} = this.state;
		
		if(aId){
			window.windowExtend.post("article/getRelationList", {id: aId}).then(res=>{
				this.setState({
					list: res.data,
					loading: false,
				})
			})
		}else{
			window.windowExtend.post("product/getSourceList", {resourcesTypeId: tId, type: type}).then(res=>{
				this.setState({
					list: res.data,
					loading: false,
				})
			})
		}
	}
	
	getList() {
		let data = this.state.list;
		let array = [];
		
		data.map((item, index) => {
			
			let imgStyle = {
				backgroundImage: `url("${item.cover}")`,
			};
			
			array.push(
				<Link to={{pathname: "/product", query:{pId: item.id,}}} key={index}>
					<li className="item" style={imgStyle}>
						<p className="title">{item.title}</p>
					</li>
				</Link>
			)
		});
		
		return array;
	}
	
	render() {
		return <div className="page-relation-list">
			
			<ul className="list">
				{this.getList()}
			</ul>
			
			<Loading loading={this.state.loading}/>
		</div>
	}
}