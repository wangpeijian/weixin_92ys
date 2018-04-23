/**
 * Created by peijian.wang on 2016/5/6.
 */
import React from 'react';
import '../../css/component/Toast';

/**
 * 页面提示框
 * onTime ： 到时间后执行
 * time ： 显示时长
 * tip ： 提示内容
 * toastStyle ：控制提示框样式
 */
export default class Toast extends React.Component{
    constructor(props){
        super(props);
    }

    componentDidMount(){
        let time = !!this.props.time ? this.props.time : 3000;
        setTimeout(() => {this.props.onTime()}, time);
    }

    render(){

        let toastStyle = this.props.toastStyle || {};

        return <div className='component-toast'>
	        <div className="toast-bg">
		        <div className="toast-box" style={toastStyle}>
			        {this.props.tip}
		        </div>
	        </div>
        </div>
    }
}