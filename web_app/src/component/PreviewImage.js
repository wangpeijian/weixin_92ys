/**
 * Created by peijian.wang on 2016/8/16.
 */
import React from 'react';

import '../../css/component/PreviewImage';

/**
 * 页面中的加载效果
 *
 * 2016-08-11
 * 王佩剑
 *
 * previewUrl : 需要预览的图片地址
 */
export default class PreviewImage extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            previewUrl: props.previewUrl,
        }
    }

    //默认加载数据
    componentDidMount(){

    }

    componentWillReceiveProps(nextProps){
        this.setState({
            previewUrl: nextProps.previewUrl,
        })
    }

    render(){

        if(!!this.state.previewUrl){

            let imgStyle = {
                background: "url('" + this.state.previewUrl + "') center / contain no-repeat",
            }

            return <div className="component-preview-image">
	            <div className='preview-image-bg' onClick={this.props.close}>
		            <div className="preview-image" style={imgStyle}></div>
	            </div>
            </div>
        }else{
            return(<div></div>);
        }

    }
}