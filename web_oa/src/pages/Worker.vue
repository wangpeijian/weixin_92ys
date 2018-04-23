<style lang="scss" scoped>


</style>

<template>

    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-head am-cf">
                        <div class="widget-title  am-cf">员工列表</div>
                    </div>

                    <div class="widget-body  am-fr">
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-3 am-u-lg-offset-9">
                            <router-link to="/worker/edit">
                                <button type="button" class="am-btn am-btn-success am-fr">添加</button>
                            </router-link>
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
                                    <th>风格</th>
                                    <th>岗位</th>
                                  <!--  <th>最后登录</th>
                                    <th>注册时间</th>-->
                                    <th>操作</th>
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
                                    <td>{{item.style}}</td>
                                    <td>{{item.jobType.name}}</td>
                                   <!-- <td>{{item.lastTime | time-format}}</td>
                                    <td>{{item.registerTime | time-format}}</td>-->
                                    <td class="btn-group">
                                        <button class="am-btn am-btn-secondary am-btn-xs am-round" @click="editDesigner(item.id)">编辑</button>
                                        <button class="am-btn am-btn-danger am-btn-xs am-round" @click="removeDesigner(item.id)">删除</button>
                                    </td>
                                </tr>
                                <!-- more data -->
                                </tbody>
                            </table>
                        </div>

                        <c-paging @changePage="getPageData" :total="total"/>

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
                this.post(`user/getList?type=worker`, {keyword: this.search, current: page}).then(res => {
                    this.total = res.data.count;
                    this.list = res.data.list;
                })
            },

            editDesigner(id){
                this.$router.push(`/worker/edit?id=${id}`);
            },

            removeDesigner(id){
                if(confirm("确定删除吗？")){
                    this.post("user/delete", {id: id}).then(res => {
                        this.getPageData(1);
                    })
                }
            },
        },

        components: {
        }
    }
</script>