<style lang="scss" scoped>

</style>

<template>
    <div class="">

        <el-table
                v-loading="loading"
                :data="tableData"
        >
            <el-table-column
                    align="center"
                    prop="id"
                    label="订单号"
                    min-width="300"
            >
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="time"
                    label="下单时间"
                    min-width="200"
            >
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="customer"
                    label="客户"
                    min-width="100"
            >
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="phone"
                    label="客户电话"
                    min-width="140"
            >
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="product"
                    label="商品"
                    min-width="180"
            >
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="remark"
                    label="备注"
                    min-width="300"
            >
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="status"
                    label="状态"
                    min-width="80"
            >
            </el-table-column>
        </el-table>
        <br>
        <el-pagination
                @current-change="pageChange"
                :current-page="currentPage"
                :page-size="10"
                layout="total, prev, pager, next, jumper"
                :total="total">
        </el-pagination>

    </div>
</template>

<script>
    export default{
        data(){
            return {
                tableData: [],
                currentPage: 1,
                total: 0,
                loading: false,
            }
        },

        created() {
            this.getTableData();
        },

        methods: {
            getTableData(){
                this.loading = true;
                let user = this.getSession(this.KEYS.USER_INFO);
                this.post(`order/getList?userId=${user.id}`, {current: this.currentPage}).then(res => {
                    this.total = res.data.count;
                    this.tableData = res.data.list.map(function(obj){

                        return {
                            id: obj.id,
                            time: new Date(obj.date).Format("yyyy-MM-dd hh:mm"),
                            customer: obj.customer.name,
                            phone: obj.customer.phone,
                            product: obj.title,
                            remark: obj.remark,
                            status: obj.status === 1 ? "已完成" : "进行中"
                        }
                    });
                    this.loading = false;
                })
            },

            pageChange(page){
                this.currentPage = page;

            }
        },

        components: {}
    }
</script>