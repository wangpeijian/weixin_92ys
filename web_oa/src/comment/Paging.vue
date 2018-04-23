<style lang="scss" scoped>
    .am-disabled {
        cursor: not-allowed;
    }
</style>

<template>
    <div class="am-u-lg-12 am-cf">

        <div class="am-fr">
            <ul class="am-pagination tpl-pagination">

                <li class="am-disabled"><a href="javascript:;">共{{pages}}页（{{total}}条）</a></li>


                <li :class="{'am-disabled': page == 1}" @click="changePage(1)"><a href="javascript:;">«</a></li>

                <li :class="{'am-active': item == page}" v-for="(item, index) in array" :key="item"
                    @click="changePage(item)">
                    <a href="javascript:void(0);">{{item}}</a>
                </li>


                <li :class="{'am-disabled': page == pages}" @click="changePage(pages)"><a href="javascript:;">»</a></li>
            </ul>
        </div>
    </div>
</template>

<script>
    export default{
        props: {
            total: Number,

            paging: {
                type: Number,
                default: 10
            }
        },

        created() {

        	let total = this.total;
        	let paging = this.paging;

        	if(total === 0){
                this.pages = 1;
            }else if (total % paging === 0) {
                this.pages = Math.ceil(total / paging);
            } else {
                this.pages = Math.ceil(total / paging + 1);
            }

            this.updateArray(1);
        },

        data(){
            return {
                pages: 1,
                array: [],
                page: 1,
            }
        },

        methods: {

            updateArray(page){

                let pages = this.pages;

                if (page <= 3) {

                	let array = [];

                	for(let i = 1; i <= this.pages; i++){
                        array.push(i);
                    }

                    this.array = array;
                } else if (page >= pages - 2) {
                    this.array = [pages - 4, pages - 3, pages - 2, pages - 1, pages];
                } else {
                    this.array = [page - 2, page - 1, page, page + 1, page + 2];
                }

            },

            changePage(page){
                if(page === this.page){
                	return;
                }

                this.page = page;

                this.updateArray(page);

                this.$emit('changePage', this.page);
            }

        },

        components: {}
    }
</script>