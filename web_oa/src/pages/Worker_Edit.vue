<style lang="scss" scoped>

</style>

<template>
    <div class="content">
        <div class="am-form">
            <c-upload-image :defaultUrl="imgUrl" btnName="上传头像" scoped="user_head"/>

            <div class="am-form-group">
                <input type="text" class="form-input am-round" placeholder="姓名" maxlength="20" v-model="name">
            </div>

            <div class="am-form-group">
                <input v-if="isAdd" type="text" class="form-input am-round" placeholder="账号" maxlength="20" v-model="username">
                <input v-else="isAdd" disabled type="text" class="form-input am-round" placeholder="账号" maxlength="20" v-model="username">
            </div>

            <div class="am-form-group">
                <input type="password" class="form-input am-round" placeholder="密码" maxlength="20" v-model="password">
            </div>

            <div class="am-form-group">
                <input type="text" class="form-input am-round" placeholder="手机号" maxlength="11" v-model="phone">
            </div>

            <div class="am-form-group">
                <input type="text" class="form-input am-round" placeholder="设计风格（分号隔开）" maxlength="50" v-model="style">
            </div>

            <div class="am-form-group">
                <select v-model="sex" class="am-round">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>

            <div class="am-form-group">
                <select v-model="jobTypeId" class="am-round">
                    <option value="">请选择职位</option>
                    <option v-for="(item, index) in typeList " :key="index" :value="item.id">{{item.name}}</option>
                </select>
            </div>

            <div class="am-form-group">
                <textarea class="form-input am-round" placeholder="简介" maxlength="200" v-model="remark"></textarea>
            </div>

        </div>

        <div class="btn-group">

            <button type="button" class="am-btn am-btn-success submit-btn" @click="save">
                <i class="am-icon-save"></i>
                保存
            </button>
        </div>
    </div>
</template>

<script>
    export default{
        data(){
            return {
                isAdd: true,
                userId: '',
                imgUrl: '',
                name: '',
                username: '',
                password: '',
                phone: '',
                sex: '男',
                style: '',
	            jobTypeId: '',
	            typeList: [],
	            remark: "",
            }
        },

        created(){
        	//获取岗位列表
            this.post("jobType/getList").then(res => {
                this.typeList = res.data;
            }).then(()=>{

	            let id = this.$route.query.id;
	            if (id !== undefined) {
		            this.isAdd = false;
		            this.userId = id;
		            //如果是编辑页面则初始化
		            this.init();
	            }

            });
        },

        mounted() {

        },

        methods: {
            init(){
                this.post("user/getDetail", {
                    id: this.userId,
                }).then(res => {
                    let data = res.data;
                    this.imgUrl = data.head;
                    this.name = data.name;
                    this.username = data.username;
                    this.password = data.password;
                    this.phone = data.phone;
                    this.sex = data.sex;
                    this.style = data.style;
	                this.jobTypeId = data.jobTypeId;
	                this.remark = data.remark;
                });
            },

            save(){
                if (this.name.trim() === "") {
                    alert("请输姓名");
                    return;
                }

                if (this.username.trim() === "") {
                    alert("请输账号");
                    return;
                }

                if (this.password.trim() === "") {
                    alert("请输密码");
                    return;
                }

                if (this.phone.trim() === "") {
                    alert("请输手机号");
                    return;
                }

	            this.$children[0].getImagesUrl().then(url => {
		            this.imgUrl = url;
		            this.submit();
	            });
            },

            submit(){
                this.post("user/saveWorker", {
                    id: this.userId,
                    head: this.imgUrl,
                    name: this.name,
                    username: this.username,
                    password: this.password,
                    phone: this.phone,
                    sex: this.sex,
                    style: this.style,
	                jobTypeId: this.jobTypeId,
	                remark: this.remark,
                }).then(res => {

                	if(res.code !== 0){
		                alert(res.msg);
		                return;
                    }

                    alert("保存成功");
                    this.$router.go(-1);
                })
            },

        },

        components: {
        }
    }
</script>