<style lang="scss" scoped>
    .preview-img {
        width: 150px;
        height: 150px;
        margin: 0 0 1.5rem 0;
        display: inline-block;
    }
</style>

<template>
    <div class="upload-block">
        <div class="am-form-group am-form-file">
            <button type="button" class="am-btn am-btn-danger am-btn-sm">
                <i class="am-icon-cloud-upload"></i>{{btnName}}

            </button>
            <input type="file" @change="changeFile">
        </div>

        <img class="preview-img" v-if="imgUrl != ''" :src="imgUrl">
    </div>
</template>

<script>
    export default{
        props: {
            defaultUrl: {
                type: String,
                default: "",
            },

            btnName: {
                type: String,
                default: "选择图片",
            },

	        index: {
		        type: Number,
		        default: 0,
	        },

	        scoped: {
		        type: String,
		        default: "",
            },

        },

        data(){
            return {
	            fileName: "",
                imgUrl: this.defaultUrl,
            }
        },

        methods: {
            changeFile(event){
                let files = event.target.files;
                let file = files[0];
                let fileReader = new FileReader();

                fileReader.onload = res => {
                    this.imgUrl = res.target.result;
                    this.fileName = file.name;
                }

                fileReader.readAsDataURL(file);
            },

	        getImagesUrl(){
		        return this.postBase64({
			        scoped: this.scoped,
			        imgUrl: this.imgUrl,
			        fileName: this.fileName,
		        })
            },
        },

	    watch: {
		    defaultUrl(){
		    	this.imgUrl = this.defaultUrl;
            }
        },

        components: {}
    }
</script>