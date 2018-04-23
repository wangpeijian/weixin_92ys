<style lang="scss" scoped>
    .multiple-file-upload-block{
        overflow: hidden;

        .single-box{
            float: left;
            padding: 10px;
            position: relative;

            &.add-box{
                width: 150px;
                height: 84px;
                text-align: center;
            }

            .remove-icon{
                display: inline-block;
                background: url("../assets/icon_remove_btn.png") center / contain;
                height: 15px;
                width: 15px;
                position: absolute;
                top: 2px;
                right: 2px;
                cursor: pointer;
            }

            .back-icon{
                display: inline-block;
                background: url("../assets/icon_back_btn.png") center / contain;
                height: 20px;
                width: 20px;
                position: absolute;
                top: 40%;
                left: 10px;
                cursor: pointer;
            }

            .forward-icon{
                display: inline-block;
                background: url("../assets/icon_forward_btn.png") center / contain;
                height: 20px;
                width: 20px;
                position: absolute;
                top: 40%;
                right: 10px;
                cursor: pointer;
            }

            .preview-img{
                width: 150px;
                height: 84px;
                border-radius: 4px;
            }

            .add-icon{
                display: inline-block;
                background: url("../assets/icon_add_image_btn.png") center / contain;
                height: 64px;
                width: 64px;
                position: relative;
                cursor: pointer;

                input[type=file]{
                    visibility: hidden;
                }
            }
        }
    }
</style>

<template>
    <div class="multiple-file-upload-block">
        <div class="single-box" v-for="item, index in images" :key="new Date().getTime()">
            <span class="remove-icon" @click="remove(index)"></span>
            <span class="back-icon" @click="back(index)" v-if="index != 0"></span>
            <img class="preview-img" :src="item.imgUrl">
            <span class="forward-icon" @click="forward(index)" v-if="index != images.length -1"></span>
        </div>

        <div class="single-box add-box">
            <label>
                <span class="add-icon">
                    <input type="file" @change="changeFile" :key="new Date().getTime()">
                </span>
            </label>
        </div>
    </div>
</template>

<script>
    export default{
        props: {
            defaultUrl: {
                type: Array,
                default: ()=>{
                    return [];
                },
            },

            scoped: {
                type: String,
                default: ()=>{
                    return "";
                },
            }
        },

        data(){
            return {
                images: []
            }
        },

        methods: {
            changeFile(event){
                let files = event.target.files;
                let file = files[0];
                let fileReader = new FileReader();

                fileReader.onload = res => {
                    this.images.push({
                        imgUrl: res.target.result,
                        fileName: file.name,
                    })
                }

                fileReader.readAsDataURL(file);
            },

            remove(index){
                this.images.splice(index, 1);
            },

            back(index){
                this.images.splice(index - 1, 0, this.images.splice(index, 1)[0]);
            },

            forward(index){
                this.images.splice(index + 1, 0, this.images.splice(index, 1)[0]);
            },

            getImagesUrl(){
                return Promise.all(this.images.map(obj => {
                    return this.postBase64({
                        scoped: this.scoped,
                        imgUrl: obj.imgUrl,
                        fileName: obj.fileName,
                    });
                })).then((res)=>{

                    this.images = this.images.map((obj, index) => {
                        obj.imgUrl = res[index];
                        return obj;
                    });

                    return res;
                })
            },
        },

	    watch: {
		    defaultUrl(){
			    this.images = this.defaultUrl;
		    }
	    },

        components: {}
    }
</script>