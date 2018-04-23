<style lang="scss" scoped>
    .form-line{
        text-align: center;
        padding: 15px 0;

        input{
            text-align: center;
        }

        .save-icon{
            display: inline-block;
            background: url("../assets/icon_save_btn.png") center / contain;
            height: 30px;
            width: 30px;
            position: relative;
            top: 4px;
            cursor: pointer;
        }

        .remove-icon{
            display: inline-block;
            background: url("../assets/icon_remove_btn.png") center / contain;
            height: 20px;
            width: 20px;
            position: relative;
            top: 9px;
            cursor: pointer;
        }

        .add-icon{
            display: inline-block;
            background: url("../assets/icon_add_btn.png") center / contain;
            height: 30px;
            width: 30px;
            position: relative;
            top: 4px;
            cursor: pointer;
        }

    }

</style>

<template>

    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">

                <div class="am-g form-line" v-for="(item, index) in typeList " :key="index">

                    <div class="am-u-sm-4">
                        <c-upload-image :defaultUrl="item.cover" btnName="上传封面" :index="index" scoped="resources_type_cover"/>  <!--v-on:changeImage="updateUrl"-->
                    </div>

                    <div class="am-u-sm-6">
                        <input type="text" class="am-form-field am-round" placeholder="输入资源类型" v-model="item.name"/>
                    </div>
                    <div class="am-u-sm-1">
                        <span class="save-icon" @click="save(index)"></span>
                    </div>
                    <div class="am-u-sm-1 am-u-end">
                        <span class="remove-icon" @click="remove(item.id)"></span>
                    </div>
                </div>

                <div class="am-g form-line">
                    <span class="add-icon" @click="addNewLine"></span>
                </div>

            </div>
        </div>
    </div>

</template>

<script>
	export default{
		data(){
			return {
				typeList: [

				]
			}
		},

		created() {
			this.getList();
		},

		methods: {
			getList(){
				this.post("resourcesType/getList").then(res => {
					this.typeList = res.data;
				});
			},

			addNewLine(){
				c.log(this.$children)

				let last = this.typeList[this.typeList.length - 1];

				if(!!last && last.name == ""){
					return false;
				}

				this.typeList.push({
					name: '',
					id: '',
					cover: '',
				});

			},

			save(index){
				let item = this.typeList[index];

				if(!item.name){
					alert("请输入名称");
					return false;
                }

                this.$children[index].getImagesUrl().then(url => {
	                item.cover = url;
	                this.submit(item);
                });
			},

			submit(item){
				this.post("resourcesType/save", item).then(res => {
					item = res.data;
				});
            },

			remove(id){
				if(confirm("确认删除吗?")){
					this.post("resourcesType/delete",{
						id: id,
					}).then(res => {
						this.getList();
					});
				}
			},

		},

		components: {
		}
	}
</script>