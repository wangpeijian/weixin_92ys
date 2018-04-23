/**
 * Created by dx on 16-11-7.
 */

import React from 'react';

import '../../css/component/SliderBlock';

/**
 * 左右滑动的组件
 *
 * 嵌套在组件内的子元素会左右滚动
 *
 * 2016-11-07
 * 王佩剑
 *
 */
export default class SliderBlock extends React.Component{
    constructor(props){
        super(props);

        this.state = {

        }

        global.scrollStartX = 0;

        global.scrollMarkX = 0;
        global.scrollMarkY = 0;

        global.windowWidth = document.body.clientWidth;
    }

    static defaultProps = {
        slideCallback : (addOrRemove)=>{console.log(addOrRemove)},

        htmlArray: [1,2,3]
    }

    //用户手指滑动
    touchStart(e){
        global.scrollStartX = e.targetTouches[0].screenX;

        global.scrollMarkX = e.targetTouches[0].screenX;
        global.scrollMarkY = e.targetTouches[0].screenY;

        return e.stopPropagation();
    }

    touchMove(e){
        //纵向滑动大于横向滑动时，不阻止浏览器默认行为，触发浏览器滚动条滑动效果
        if(Math.abs(e.targetTouches[0].screenY - global.scrollMarkY) < Math.abs(e.targetTouches[0].screenX - global.scrollMarkX)){
            e.preventDefault();

            let list = e.currentTarget.children[0];
            let left = list.style.left.replace(/px/g, "");

            list.style.left = (~~left - ~~(global.scrollStartX - e.targetTouches[0].screenX)) + "px";
            global.scrollStartX = e.targetTouches[0].screenX;
        }

        return e.stopPropagation();
    }

    touchEnd(e){
        global.scrollStartX = 0;

        let list = e.currentTarget.children[0];
        let left = list.style.left.replace(/px/g, "");

        //列表往右滑动，显示上一个列表
        let addOrRemove = 1;

        if(-left / global.windowWidth <= 0.5 ){
            addOrRemove = -1;
        }else if(-left / global.windowWidth >= 1.5 ){
            addOrRemove = 1;
        }else{
            addOrRemove = 0;
        }

        if(!addOrRemove === 0){
            this.props.slideCallback(addOrRemove);
        }

        //列表归位
        list.style.left =  - global.windowWidth + "px";
    }

    render(){

        return <div className="component-slider-block">
                <div className="scroll-block" onTouchStart={this.touchStart.bind(this)}
                     onTouchMove={this.touchMove.bind(this)}
                     onTouchEnd={this.touchEnd.bind(this)}>

                    <ul className="slide-block" style={{left: -global.windowWidth + "px"}}>
                        <li className="item-block before-block">{this.props.htmlArray[0]}</li>
                        <li className="item-block current-block">{this.props.htmlArray[1]}</li>
                        <li className="item-block after-block">{this.props.htmlArray[2]}</li>
                    </ul>

                </div>
        </div>
    }
}