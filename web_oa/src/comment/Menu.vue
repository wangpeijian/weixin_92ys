<style lang="scss" scoped>
    .left-sidebar{
        padding-top: 0 !important;

        .tpl-user-panel-profile-picture{
            width: 100%;
            text-align: center;

            img{
                height: 82px;
                width: 82px;
                border-radius: 50%;
                display: inline-block;
            }
        }

        .user-panel-logged-in-text{
            text-align: center;
        }

        .sidebar-nav.root-nav{
            position: absolute;
            top: 165px;
            bottom: 0;
            width: 100%;
            overflow: auto;

            .son{
                a.active{
                    border: none;
                    background: none;
                }
            }
        }
    }
</style>

<template>
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <img :src="user.head">
                </div>
                <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              {{user.name}}
          </span>
            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav root-nav">

            <li v-for="(item, index) in menu" class="sidebar-nav-link" :key="index">
                <div v-if="item.son == undefined" @click="changePage(index)">
                    <router-link :to="item.link" :class="{active: item.isActive}" replace>
                        <i class="sidebar-nav-link-logo" :class=[item.icon]></i>
                        {{item.name}}
                    </router-link>
                </div>

                <div v-else="item.son == undefined">
                    <a href="javascript:;" class="sidebar-nav-sub-title" :class="{active: item.isActive}" @click="toggleMenu(index)">
                        <i class="am-icon-table sidebar-nav-link-logo"></i>
                        {{item.name}}
                        <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"
                              :class="{'sidebar-nav-sub-ico-rotate': item.display}"></span>
                    </a>
                    <ul class="sidebar-nav sidebar-nav-sub" :style="{ display: item.display ? 'block' : 'none' }">
                        <li v-for="(s_item, s_index) in item.son" class="sidebar-nav-link son" :key="s_index" @click="changePage(s_index, index)">
                            <router-link :to="s_item.link" :class="{active: s_item.isActive}" replace>
                                <i class="sidebar-nav-link-logo" :class=[s_item.icon]></i> {{s_item.name}}
                            </router-link>
                        </li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>

    export default{
        data(){

            let user = this.getSession(this.KEYS.USER_INFO);
            let code = user.role.code;

            if(code !== "admin"){
            	return {};

            }

            return {
                user: user,
                menu: [
	                {
		                name: '首页',
		                icon: 'am-icon-home',
		                link: '/',
		                isActive: true,
	                },

	                {
		                name: '订单',
		                icon: 'am-icon-key',
		                link: '/order',
		                isActive: false,
	                },

//	                {
//		                name: '产品',
//		                icon: 'am-icon-home',
//		                link: '/product/add',
//		                isActive: false,
//	                },

	                {
		                name: '人员管理',
		                icon: 'am-icon-home',
		                link: false,
		                isActive: false,
		                display: false,

		                son: [
			                {
				                name: '用户',
				                icon: 'am-icon-key',
				                link: '/user',
				                isActive: false,
			                },
			                {
				                name: '员工',
				                icon: 'am-icon-key',
				                link: '/worker',
				                isActive: false,
			                }
		                ]
	                },

	                {
		                name: '模块管理',
		                icon: 'am-icon-home',
		                link: false,
		                isActive: false,
		                display: false,

		                son: [
			                {
				                name: '文章',
				                icon: 'am-icon-key',
				                link: '/article',
				                isActive: false,
			                },
			                {
				                name: '企业服务',
				                icon: 'am-icon-key',
				                link: '/service',
				                isActive: false,
			                },
			                {
				                name: '创意分享',
				                icon: 'am-icon-key',
				                link: '/resource',
				                isActive: false,
			                },
			                {
				                name: '私人设计',
				                icon: 'am-icon-key',
				                link: '/personal',
				                isActive: false,
			                },
		                ]
	                },

	                {
		                name: '其他',
		                icon: 'am-icon-home',
		                link: false,
		                isActive: false,
		                display: false,

		                son: [
			                {
				                name: '岗位类型',
				                icon: 'am-icon-key',
				                link: '/job_type',
				                isActive: false,
			                },
			                {
				                name: '资源类型',
				                icon: 'am-icon-key',
				                link: '/resources_type',
				                isActive: false,
			                },
		                ]
	                },
                ],
            }
        },

        mounted(){

        },

        created() {

        },

        methods: {
            changePage(index, p_index){
                function clear(arr){
                    arr.map(function(obj, i){
                        obj.isActive = false;

                        if(obj.son != undefined){
                            clear(obj.son);
                        }
                        return obj;
                    })
                }

                clear(this.menu);

                if(p_index == undefined){
                    this.menu.map(function(obj, i){

                        if(index == i){
                            obj.isActive = true;
                        }

                        return obj;
                    });
                }else{
                    this.menu[p_index].isActive = true;

                    this.menu[p_index].son.map(function(obj, i){

                        if(index == i){
                            obj.isActive = true;
                        }

                        return obj;
                    });
                }

            },

	        toggleMenu(index){


		        this.menu.map((obj, i)=>{

		        	if(!!obj.son){

		        		if(index == i){
					        obj.display = !obj.display;
                        }else{
					        obj.display = false;
                        }

                    }

                })

            },
        },

        components: {}
    }
</script>