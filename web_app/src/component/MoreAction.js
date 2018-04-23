/**
 * Created by dx on 16-11-8.
 */

import React from 'react';

import '../../css/component/MoreAction';

/**
 * 头部右上角更多操作的弹出曾
 *
 * 2016-11-08
 * 王佩剑
 *
 */
export default class MoreAction extends React.Component{
    constructor(props){
        super(props);
    }

    static defaultProps = {
        select : () => {},
        close : () => {},
        actionArray: [],
    }

    getActionListHTML(){
        let array = [];

        for(let i in this.props.actionArray){
            array.push(
                <div className="item" key={i} onClick={this.props.select.bind(this, i)}>{this.props.actionArray[i]}</div>
            )
        }

        return array;
    }

    render(){

        return <div className="component-more-action">
                    <div>

                        <div className="mask" onClick={this.props.close.bind(this)}></div>

                        <div className="add-list">
                            <span className="arrow"></span>
                            {this.getActionListHTML()}
                        </div>

                    </div>
        </div>
    }
}