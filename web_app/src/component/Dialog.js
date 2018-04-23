/**
 * Created by peijian.wang on 2016/4/14.
 */
import React from 'react';
import '../../css/component/Dialog';

/**
 * 操作提示框
 *
 * textTitle ： 对话框标题
 * ok ： 点击确认事件
 * textOk ：确认按钮文字
 * cancel ： 点击取消事件
 * textCancel ： 取消按钮文字
 * textTip ： 对话框提示文字
 *
 * visible ： 对话框是否展示
 *
 * 备注：若只传ok则对话框展示一个按钮
 */
export default class Dialog extends React.Component{
    constructor(props){
        super(props);
    }
	
	static defaultProps = {
		textTitle: "提示",
		ok: () => {},
		textOk: "",
		cancel: () => {},
		textCancel: undefined,
		textTip: "",
		visible: false,
	}
    

    render(){
    	
    	if(!this.props.visible){
    		return null;
	    }
    	

        let button = '';
        //没有取消按钮（只有一个按钮）
        if(this.props.textCancel === undefined){
            button = <div className = "big-btn" onClick={this.props.ok}>{this.props.textOk}</div>
        }else{
            button = <div>
                <div className = "min-btn cancel-btn" onClick={this.props.cancel}>{this.props.textCancel}</div>
                <div className = "min-btn ok-btn" onClick={this.props.ok}>{this.props.textOk}</div>
            </div>
        }

        return <div className="component-dialog">
            <div className="dialog-mask"></div>
            <div className="dialogBg">
                <div className="dialog">
                    <div className="dialog-header">{this.props.textTitle}</div>
                    <div className="dialog-tip">{this.props.textTip}</div>
                    <div className="dialog-bottom">
                        {button}
                    </div>
                </div>
            </div>
        </div>
    }
}