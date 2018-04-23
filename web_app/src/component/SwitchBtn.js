/**
 * Created by dx on 16-9-18.
 */
import React from 'react';

import '../../css/component/SwitchBtn';

/**
 * 页面中的开关按钮
 *
 * 2016-09-18
 * 王佩剑
 *
 * switch : 开关状态切换后回调函数
 *
 * checked : 当前是否被选中
 *
 * disabled : 禁止按钮
 */
export default class SwitchBtn extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            id: "",
            checked : props.checked,
            disabled : props.disabled,
        }
    }

    componentWillReceiveProps(nextProps) {
        let checked = nextProps.checked;
        if(nextProps.disabled === true){
            checked = false;

            try{
                this.refs.switchBtn.checked = false;
            }catch (e){

            }

        }else {
            if(nextProps.checked){
                this.refs.switchBtn.checked = true;
                checked = true;
            }else {

                try{
                    this.refs.switchBtn.checked = false;
                }catch (e){

                }

                checked = false;
            }
        }

        this.setState({
            checked: checked,
            disabled : nextProps.disabled,
        })

    }

    //选择积分悬赏开关
    switchChange(){
        let switchBtn = this.refs.switchBtn;

        if(switchBtn.checked == this.state.checked){
            return;
        }

        if(switchBtn.checked){
            this.props.switch(true);
        }else{
            this.props.switch(false);
        }
    }

    render() {
        if(this.state.disabled === true){
            return <span className='component-switch-btn'>
                <label>
                    <input type="checkbox" className="ios-switch green bigswitch" disabled />
                    <div className="switch-btn">
                        <div></div>
                    </div>
                </label>
        </span>
        }

        let id = this.state.id;
        let checked = "";
        if(this.state.checked){
            checked = "checked";
        }

        return <span className='component-switch-btn'>

            <label htmlFor={id} onClick={this.switchChange.bind(this)}>
                <input type="checkbox" className="ios-switch green bigswitch" ref="switchBtn" id={id} name={id} defaultChecked={checked}/>
                <div className="switch-btn">
                    <div></div>
                </div>
            </label>

        </span>
    }
}