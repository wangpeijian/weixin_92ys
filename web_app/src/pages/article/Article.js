/**
 * Created by peijian.wang on 2017/4/30.
 */
import React from 'react';
import Slider from 'react-slick';
import {Link} from 'react-router';

import Loading from '../../component/Loading';


import '../../../css/article/Article.scss';


export default class ArticleList extends React.Component {
	
	constructor(props) {
		super(props);
		
		let {aId} = this.props.location.query;
		
		this.state = {
			loading: true,
			
			aId: aId,
			
			article: {
				title: "",
				pic: [""],
				content: "",
				time: "",
			},
			
			author: {
				name: "",
				head: "",
				duty: "",
			}
			
		};
		
		window.windowExtend.setPageTitle("92 | 因愫");
	}
	
	//页面加载完毕
	componentDidMount() {
		window.windowExtend.post("article/getDetail", {id: this.state.aId}).then(res=>{
			let data = res.data;
			let author = data.user;
			let duty = author.jobType;
			
			this.setState({
				article: {
					title: data.title,
					pic: [data.cover],
					content: data.content,
					time: new Date(data.publishTime).Format("yyyy-MM-dd hh:mm"),
				},
				
				author: {
					name: author.name,
					head: author.head,
					duty: duty.name
				},
				
				loading: false,
			})
		})
	}
	
	getImages(){
		let data = this.state.article.pic;
		
		let array = [];
		
		for(let i in data){
			let item = data[i];
			
			array.push(
				<img src={item} key={i}/>
			)
		}
		
		return array;
	}
	
	render() {
		let state = this.state;
		
		return <div className="page-article">
			
			<div className="article">
				
				<h2 className="title">{state.article.title}</h2>
				
				<p className="time">
					{state.article.time}
				</p>
				
				<Slider {...windowExtend.getCarouselConfig}>
					{this.getImages()}
				</Slider>
				
				<div className="content" dangerouslySetInnerHTML={{__html: state.article.content}}>
				</div>
				
			</div>
			
			<div className="author">
				<img src={state.author.head} className="head" />
				
				<div className="info">
					<span className="name">{state.author.name}</span>
					<span className="duty">{state.author.duty}</span>
				</div>
				
				<Link to={{pathname: "/relationList", query:{aId: this.state.aId,}}}>
					<div className="btn">
						购买此类设计
					</div>
				</Link>
			</div>
			
			<Loading loading={state.loading}/>
		</div>
	}
}