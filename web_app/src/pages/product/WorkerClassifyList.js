/**
 * Created by peijian.wang on 2017/5/7.
 */
import React from 'react';
import {Link} from 'react-router';

import Loading from '../../component/Loading';

import '../../../css/product/WorkerClassifyList.scss';


export default class WorkerClassifyList extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			loading: true,
			
			active: 0,
			
			typeList: [],
			
			workerList: [],
		};
		
		window.windowExtend.setPageTitle("私设 ｜ PRIVATE DESIGN");
	}
	
	//页面加载完毕
	componentDidMount() {
		this.getDefaultData().then(()=>{
			this.getPageData();
		});
	}
	
	getDefaultData(){
		return window.windowExtend.post("jobType/getList", {}).then(res=>{
			this.setState({
				typeList: [
					{
						name: '全部',
						id: '',
					}
				].concat(res.data),
			})
		})
	}
	
	getPageData(){
		let {active,  typeList} = this.state;
		
		let id = typeList[active].id;
		
		return window.windowExtend.post("product/getWorkerDataByType", {
			id: id
		}).then(res=>{
			this.setState({
				workerList: res.data.map(obj => {
					
					let worker = obj.user;
					let carousels = obj.carousels;
					
					return {
						name: worker.name,
						style: worker.style,
						head: worker.head,
						info: "",
						img: carousels.map(img => {
							return img.url
						}),
						pid: obj.id,
						remark: worker.remark,
					}
					
				}),
				
				loading: false,
			})
		})
	}
	
	getTypeList(){
		let array = [];
		let data = this.state.typeList;
		
		data.map((obj, i) => {
			
			let className = "type-label";
			
			if(i === this.state.active){
				className += " active";
			}
			
			array.push(
				<span className={className} key={i} onClick={this.changeClassify.bind(this, i)}>
					{obj.name}
				</span>
			);
		});
		
		return array;
	}
	
	getStyleList(style){
		let array = [];
		let data = style.split(",");
		
		for(let i in data){
			
			if(i > 2){
				array.push(
					<span className="style-item" key={i}>...</span>
				);
				break;
			}
			
			let label = data[i];
			array.push(
				<span className="style-item" key={i}>{label}</span>
			);
		}
		
		return array;
	}
	
	getImg(img){
		let array = [];
		
		img.map((obj, i) => {
			array.push(
				<img src={obj} className="img-item" key={i}/>
			)
		});
		
		return array;
	}
	
	getList(){
		let array = [];
		let data = this.state.workerList;
		
		data.map((obj, i) => {
			
			
			let style = this.getStyleList(obj.style);
			let img = this.getImg(obj.img);
			
			array.push(
				<Link to={{pathname: "/product", query:{pId: obj.pid,}}} key={i}>
					<div className="item">
						<div className="header">
							<img className="head" src={obj.head}/>
							<p className="name">{obj.name}</p>
							<p className="style">{style}</p>
						</div>
						
						<div className="content">
							{img}
						</div>
						
						<div className="foot">
							{obj.remark}
						</div>
					</div>
				</Link>
			);
		});
		
		return array;
	}
	
	changeClassify(i){
		this.setState({
			active: i,
			loading: true,
		}, ()=>{
			this.getPageData();
		});
	}
	
	
	render() {
		return <div className="page-worker-classify-list">
			
			<div className="scroll-title">
				{this.getTypeList()}
			</div>
			
			<div className="list-content">
				{this.getList()}
			</div>
			
			<Loading loading={this.state.loading}/>
		</div>
	}
}