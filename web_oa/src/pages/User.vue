<style lang="scss" scoped>


</style>

<template>

    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-head am-cf">
                        <div class="widget-title  am-cf">用户列表</div>
                    </div>

                    <div class="widget-body  am-fr">
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-3 am-u-lg-offset-9">
                            <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                <input type="text" class="am-form-field" v-model="search" placeholder="搜索 姓名\手机号">
                                <span class="am-input-group-btn" @click="getPageData(1)">
                                    <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"
                                            type="button"></button>
                                </span>
                            </div>
                        </div>

                        <div class="block"></div>

                        <div class="am-u-sm-12">
                            <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black "
                                   id="example-r">
                                <thead>
                                <tr>
                                    <th>头像</th>
                                    <th>昵称</th>
                                    <th>手机</th>
                                    <th>性别</th>
                                    <th>省份</th>
                                    <th>城市</th>
                                    <th>最后登录</th>
                                    <th>注册时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="gradeX" v-for="(item, index) in list">
                                    <td>
                                        <img :src="item.head"/>
                                    </td>
                                    <td>{{item.name}}</td>
                                    <td>{{item.phone}}</td>
                                    <td>{{item.sex}}</td>
                                    <td>{{item.province}}</td>
                                    <td>{{item.city}}</td>
                                    <td >{{item.lastTime | time-format}}</td>
                                    <td >{{item.registerTime | time-format}}</td>
                                </tr>
                                <!-- more data -->
                                </tbody>
                            </table>
                        </div>

                        <c-paging @changePage="getPageData" :total="total" />

                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
    export default{
        data(){
            return {
                search: '',
                total: 0,
                list: [],
            }
        },

        created() {
            this.getPageData(1);
        },

        methods: {
            getPageData(page){

                this.post(`user/getList?type=user&keyword=${this.search}`, {current: page}).then(res => {
                    this.total = res.data.count;
                    this.list = res.data.list;
                })

            }
        },

        components: {
        }
    }
</script>