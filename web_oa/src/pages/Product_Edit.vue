<style lang="scss" scoped>
    .multiple-img-block{
        margin-bottom: 1.5rem;
    }

    .am-panel{
        margin-bottom: 1.5rem;

        .am-panel-title{
            input{
                display: inline-block;
                background: transparent;
                width: 50%;
                border: none;
                border-bottom: 1px solid #fff;
            }
        }

        .am-panel-bd{

            .item-group{
                overflow: hidden;
                margin: 20px;

                input{
                    border: none;
                    border-bottom: 1px solid #03a9f3;
                    box-shadow: none;
                }
            }

            .item-add-btn{
                text-align: center;
            }
        }
    }
</style>

<template>
    <div class="content">
        <div class="am-form">

            <c-upload-image :defaultUrl="imgUrl" btnName="上传封面" scoped="product_cover" ref="coverImg" />

            <div class="am-form-group">
                <input type="text" class="form-input am-round" placeholder="输入标题" maxlength="20" v-model="title">
            </div>

            <c-multiple-file-upload :defaultUrl="carousels" class="multiple-img-block" scoped="product_carousel" ref="carouselImg" />

            <div class="am-form-group">
                <select v-model="type" class="am-round">
                    <option v-for="(item, index) in typeList " :key="index" :value="index">{{typeList[index]}}</option>
                </select>
            </div>

            <div class="am-form-group">
                <select v-model="resourcesTypeId" class="am-round" v-show="showResourcesType">>
                    <option value="">选择资源类型</option>
                    <option v-for="(item, index) in resourcesTypeList " :key="index" :value="item.id">{{item.name}}</option>
                </select>
            </div>

            <div class="am-form-group">
                <select v-model="haveAttachment" class="am-round">
                    <option value="0">不含有附件</option>
                    <option value="1">含有附件</option>
                </select>
            </div>

            <div class="am-form-group" v-show="showAuthor">
                <select v-model="authorId" class="am-round">
                    <option value="" v-if="type !== 'personal'">92出品</option>
                    <option v-for="(item, index) in workerList " :key="index" :value="item.id">{{item.name}}</option>
                </select>
            </div>

            <div class="am-form-group">
                <select v-model="articleId" class="am-round">
                    <option value="">选择关联的文章</option>
                    <option v-for="(item, index) in articleList " :key="index" :value="item.id">{{item.title}}</option>
                </select>
            </div>

            <div class="am-form-group">
                <input type="number" class="form-input am-round" placeholder="折扣" maxlength="3" v-model="discount">
            </div>


            <template v-if="this.needAttachmentInfo">
                <div class="am-form-group">
                    <input type="text" class="form-input am-round" placeholder="输入网盘连接"  v-model="attachmentURL">
                </div>

                <div class="am-form-group">
                    <input type="text" class="form-input am-round" placeholder="输入网盘密码" maxlength="30" v-model="attachmentPassword">
                </div>
            </template>

            <template v-if="this.needDirectorInfo">
                <div class="am-form-group">
                    <input type="text" class="form-input am-round" placeholder="输入负责人" maxlength="10" v-model="director">
                </div>

                <div class="am-form-group">
                    <input type="text" class="form-input am-round" placeholder="输入负责人电话" maxlength="11" v-model="directorPhone">
                </div>
            </template>

            <div class="am-panel-group">
                <div class="am-panel am-panel-primary" v-for="(g_item, g_index) in itemsGroup " :key="g_index">
                    <header class="am-panel-hd">
                        <h3 class="am-panel-title">
                            <input type="text" v-model="g_item.name" placeholder="服务项目">

                            <button type="button" class="am-btn am-btn-danger am-btn-xs am-fr" @click="removeGroup(g_index)">删除</button>
                        </h3>
                    </header>
                    <div class="am-panel-bd">

                        <div class="item-group" v-for="(s_item, s_index) in g_item.productItems " :key="s_index">
                            <div class="am-u-sm-5">
                                <input type="text" v-model="s_item.name" placeholder="服务内容">
                            </div>

                            <div class="am-u-sm-3 am-u-sm-offset-2">
                                <input type="number" v-model="s_item.price" placeholder="价格">
                            </div>

                            <div class="am-u-sm-2">
                                <button type="button" class="am-btn am-btn-danger am-btn-xs" @click="removeItem(g_index, s_index)">删除</button>
                            </div>
                        </div>

                        <div class="item-add-btn">
                            <button type="button" class="am-btn am-btn-secondary am-round am-btn-sm" @click="addItems(g_index)">
                                添加服务内容
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="btn-group">
                <button type="button" class="am-btn am-btn-success submit-btn" @click="addItemsGroup">
                    添加服务项目
                </button>
            </div>

        </div>

        <section id="editor" class="editor-box"></section>

        <div class="btn-group">
            <button type="button" class="am-btn am-btn-success submit-btn" @click="save">
                <i class="am-icon-save"></i>
                发布产品
            </button>
        </div>
    </div>
</template>

<script>
    export default{
        data(){

        	let {id, type} = this.$route.query;

            return{
	            id: id,
                typeList: [],
	            resourcesTypeList: [],
                workerList: [],
	            articleList: [],
	            itemsGroup: [],

	            imgUrl: '',
	            carousels: [],
	            _carousels: [],
	            title: '',
                type: type,
	            haveAttachment: 0,
	            resourcesTypeId: '',
	            authorId: '',
	            articleId: '',
	            discount: 1,
	            attachmentURL: "",
	            attachmentPassword: "",
	            director: "",
	            directorPhone: "",

                showAuthor: true,
	            showResourcesType: true,
            }
        },

	    created(){

		    Promise.all([
		    	    this.post(`product/getTypeList`).then(res => {
                        this.typeList = res.data;
                       /* for (let i in res.data) {
                            this.type = i;
                            break;
                        }*/
			        }),
				    this.post("resourcesType/getList").then(res => {
					    this.resourcesTypeList = res.data;
					    //this.resourcesTypeId = res.data[0].id;
				    }),
				    this.post(`user/getWorkerList`).then(res => {
					    this.workerList = res.data;
				    }),
				    this.post(`article/getAllList`).then(res => {
					    this.articleList = res.data;
					    //this.articleId = res.data[0].id;
				    }),]
		    ).then(() => {

			    if(!!this.id){
				    this.post(`product/getDetail`, {
					    id: this.id,
				    }).then(res => {
					    let data = res.data;

					    this.imgUrl = data.cover;
					    this.carousels = data.carousels.map(function(obj){
						    return {
							    imgUrl: obj.url,
						    };
					    });
					    this._carousels = data.carousels;
					    this.title = data.title;
					    this.resourcesTypeId = data.resourcesTypeId;
					    this.authorId = data.authorId;
					    this.articleId = data.articleId || "";
					    this.discount = data.discount;
					    this.itemsGroup = data.productItems;
					    this.type = data.type;
					    this.haveAttachment = data.haveAttachment;
					    this.attachmentURL = data.attachmentURL;
					    this.attachmentPassword = data.attachmentPassword;
					    this.director = data.director;
					    this.directorPhone = data.directorPhone;
					    this.editorSetContent(data.content);
				    });
			    }

		    });

	    },

        mounted() {
            this.froalaEditorConfig();
        },

        methods : {
	        addItemsGroup(){
		        this.itemsGroup.push({
                    id: '',
                    name: '',

			        productItems: [{
				        id: '',
				        name: '',
                        price: '',
                    }],
                });
            },

	        removeGroup(g_index){
		        this.itemsGroup.splice(g_index, 1)
            },

	        addItems(index){
		        this.itemsGroup[index].productItems.push({
			        id: '',
			        name: '',
			        price: '',
                });
            },

	        removeItem(g_index, s_index){
		        this.itemsGroup[g_index].productItems.splice(s_index, 1);
            },

            save(){
	            this.$refs.carouselImg.getImagesUrl().then(res => {
		            this.submit(res);
	            });
            },


            submit(carousel){

            	//此处将没变化的图片放入新的数组里
	            let carousels = carousel.map((obj, index) => {

	            	if(!!this._carousels){
			            for(let c of this._carousels){
				            if(c.url === obj){
				            	c.no = index;
					            return c
				            }
			            }
                    }


                    return {
	            		id: '',
                        url: obj,
                        pid: '',
                        no: index,
                    }
                });

	            let req = {
		            id: this.id,
		            carousels: carousels,
		            productItems: this.itemsGroup,
		            cover: this.imgUrl,
		            title: this.title,
		            type: this.type,
		            haveAttachment: this.haveAttachment,
		            content: this.editorGetContent(),
		            resourcesTypeId: this.resourcesTypeId,
		            authorId: this.authorId,
		            articleId: this.articleId,
		            discount: this.discount,
		            attachmentURL: this.attachmentURL,
		            attachmentPassword: this.attachmentPassword,
		            director: this.director,
		            directorPhone: this.directorPhone,
	            };

	            if(!this.needAttachmentInfo){
		            req.attachmentURL = "";
		            req.attachmentPassword = "";
                }

                if(!this.needDirectorInfo){
	                req.director = "";
	                req.directorPhone = "";
                }

	            this.$refs.coverImg.getImagesUrl().then(url => {
		            this.imgUrl = url;
	            }).then(() => {
		            this.post(`product/save`, req).then(res => {
                        alert("保存成功");
			            this.$router.go(-1);
		            });
                })

            },

        },

        watch:{
	        type(){
		        this.showAuthor = true;
		        this.showResourcesType = true;

                if(this.type === "service"){
                    this.authorId = "";
                    this.resourcesTypeId = "";

	                this.showAuthor = false;
                    this.showResourcesType = false;
                }

		        if(this.type === "source"){
			        this.authorId = "";

			        this.showAuthor = false;
		        }
            }
        },

	    computed: {
	    	//网盘信息，如果选择了有附件则需要填写网盘地址和网盘密码
	        needAttachmentInfo(){
                return Number(this.haveAttachment) === 1;
            },

            //负责人信息，如果选择没有附件，并且不是私人服务，则需要填写负责人
            //私人服务的负责人就是提供服务的工人
            needDirectorInfo(){
                if(Number(this.haveAttachment) === 1){
                	return false;
                }

                return this.type !== "personal";
            },
        },

        components: {
        }
    }
</script>