/**
 * Created by peijian.wang on 2016/8/11.
 */
import React from 'react';

import '../../css/component/Loading';

/**
 * 页面中的加载效果
 *
 * 2016-08-11
 * 王佩剑
 *
 * loading : 页面是否在加载
 */
export default class Loading extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            loading: props.loading,
        }
    }

    componentWillReceiveProps(nextProps){
        if(nextProps.loading == this.state.loading){
            return;
        }

        this.setState({
            loading: nextProps.loading,
        })
    }

    render(){

        if(this.state.loading){
            return <div className='component-loading'>
                <span className="loading-img"></span>
                <span className="loading-tip">加载中</span>
            </div>
        }else{
            return(<div></div>);
        }

    }
}