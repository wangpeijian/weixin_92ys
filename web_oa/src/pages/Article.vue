<style lang="scss" scoped>
   
</style>

<template>
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-head am-cf">
                        <div class="widget-title  am-cf">文章列表</div>
                    </div>

                    <div class="widget-body  am-fr">
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-3 am-u-lg-offset-9">
                            <router-link to="/article/edit">
                                <button type="button" class="am-btn am-btn-success am-fr">新增文章</button>
                            </router-link>
                        </div>

                        <div class="block"></div>

                        <div class="am-u-sm-12">
                            <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black "
                                   id="example-r">
                                <thead>
                                <tr>
                                    <th>封面</th>
                                    <th>标题</th>
                                    <th>作者</th>
                                   <!-- <th>发布时间</th>-->
                                    <th>更新时间</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="gradeX" v-for="(item, index) in list" :key="index">
                                    <td>
                                        <img :src="item.cover" class="article-cover"/>
                                    </td>
                                    <td>{{item.title}}</td>
                                    <td>{{item.user.name}}</td>
                                    <!--<td>{{item.publishTime | time-format}}</td>-->
                                    <td>{{item.updateTime | time-format}}</td>
                                    <td>{{item.status | articleStatus}}</td>
                                    <td class="btn-group">
                                        <button class="am-btn am-btn-secondary am-btn-xs am-round" @click="editArticle(item.id)">编辑</button>

                                        <button class="am-btn am-btn-success am-btn-xs am-round" v-if="item.status == 0" @click="publish(item.id, index)">发布</button>
                                        <button class="am-btn am-btn-warning am-btn-xs am-round" v-if="item.status == 1" @click="unpublished(item.id, index)">撤回</button>

                                        <button class="am-btn am-btn-danger am-btn-xs am-round" @click="removeArticle(item.id)">删除</button>
                                    </td>
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
                total: 0,
                list: [],
            }
        },

        created() {
            this.getPageData(1);
        },

        methods: {
            getPageData(page){
                let user = this.getSession(this.KEYS.USER_INFO);
                this.post(`article/getList?userId=${user.id}`, {current: page}).then(res => {
                    this.total = res.data.count;
                    this.list = res.data.list.map(function(obj){
                    	if(obj.user === undefined){
                    		obj.user = {};
                        }

                        return obj;
                    });
                })
            },

            removeArticle(id) {
            	if(confirm("确定删除吗？")){
                    this.post("article/delete", {id: id}).then(res => {
                        this.getPageData(1);
                    })
                }
            },

            editArticle(id = ""){
                this.$router.push(`/article/edit?id=${id}`);
            },

            publish(id, index){
                this.post("article/publish", {id: id}).then(res => {
                    //this.getPageData(1);
                    this.list[index].status = 1;
                })
            },

            unpublished(id, index){
                this.post("article/unpublished", {id: id}).then(res => {
                    //this.getPageData(1);
                    this.list[index].status = 0;
                })
            }
        },

        filters: {
            articleStatus(status){
                if(status === 0){
                	return "未发布"
                }else if(status === 1){
                    return "已发布"
                }
            }
        },

        components: {
        }
    }
</script>