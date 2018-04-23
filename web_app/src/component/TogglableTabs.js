/**
 * Created by dx on 16-11-7.
 */
import React from 'react';

import '../../css/component/TogglableTabs';

export default class TogglableTabs extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            //当前被选中的标签
            active: props.active,
        }
    }

    static defaultProps = {
        tabs: [],

        selectCallback : (index) => {

        },

        width : window.innerWidth,

        active : 0,
    }

    //页面加载完毕
    componentDidMount() {

    }
	
	componentWillReceiveProps(nextProps){
		this.setState({
			active: nextProps.active,
		})
	}

    //选中了一个分类
    selectTab(index){
        this.setState({
            active: index,
        }, this.props.selectCallback.bind(this, index));
    }

    getTabsHTML(){
        let array = [];
        let tabs = this.props.tabs;
        let style = {
            width: 100 / tabs.length + "%",
        }

        for(let index in tabs){

            let className = "tab-item";

            if(index == this.state.active){
                className += " active";
            }

            array.push(
                <li className={className} key={index} style={style} onClick={this.selectTab.bind(this, index)}>
                    {tabs[index]}
                </li>
            )
        }

        return array;
    }

    render() {

        return <div className="component-togglabel-tabs">

            <ul className="tabs-bar">
                {this.getTabsHTML()}
            </ul>

        </div>
    }
}