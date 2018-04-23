<style lang="scss" scoped>

</style>

<template>
    <div class="content">
        <div class="am-form">
            <c-upload-image :defaultUrl="imgUrl" btnName="上传封面" scoped="article_cover"/>

            <div class="am-form-group">
                <input type="text" class="form-input am-round" placeholder="输入文章标题" maxlength="20" v-model="title">
            </div>

            <div class="am-form-group">
                <input type="text" class="form-input am-round" placeholder="分享文章描述" maxlength="20" v-model="shareDescribe">
            </div>

            <div class="am-form-group">
                <select v-model="authorId" class="am-round">
                    <option value="">92出品</option>
                    <option v-for="(item, index) in workerList " :key="index" :value="item.id">{{item.name}}</option>
                </select>
            </div>
        </div>

        <section id="editor" class="editor-box"></section>

        <div class="btn-group">
            <button type="button" class="am-btn am-btn-success submit-btn" @click="save">
                <i class="am-icon-save"></i>
                保存文章
            </button>
        </div>
    </div>
</template>

<script>
    export default{
        data(){
            return {
                isAdd: true,
                articleId: "",
                imgUrl: "",
                title: "",
	            workerList: [],
	            authorId: '',
	            shareDescribe: "",
            }
        },

        mounted() {
            this.froalaEditorConfig();

	        this.post(`user/getWorkerList`).then(res => {
		        this.workerList = res.data;
	        }).then(()=>{

		        let id = this.$route.query.id;
		        if (id !== undefined) {
			        this.isAdd = false;
			        this.articleId = id;
			        //如果是编辑页面则初始化
			        this.init();
		        }

            })
        },

        methods: {

            init(){
                this.post("article/getDetail", {
                    id: this.articleId,
                }).then(res => {
                    let data = res.data;
                    this.editorSetContent(data.content);
                    this.title = data.title;
                    this.imgUrl = data.cover;
                    this.authorId = data.userId;
                    this.shareDescribe = data.shareDescribe;
                })
            },

            save(){
                if (this.title.trim() === "") {
                    alert("请输入文章标题");
                    return;
                }

	            this.$children[0].getImagesUrl().then(url => {
		            this.imgUrl = url;
		            this.submit();
	            });
            },

            submit(){
                this.post("article/save", {
                    id: this.articleId,
                    title: this.title.trim(),
                    content: this.editorGetContent(),
                    userId: this.authorId,
                    cover: this.imgUrl,
	                shareDescribe: this.shareDescribe.trim(),
                }).then(res => {
                    alert("保存成功");
                    this.$router.go(-1);
                })
            },
        },

        components: {
        }
    }
</script>