<style lang="scss" scoped>
    .tpl-header-logo {
        img {
            height: 50px;
            margin: 4px auto 3px auto;
            width: auto;
        }
    }
</style>

<template>
    <header>
        <!-- logo -->

        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">

            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;">欢迎你, <span>{{name}}</span> </a>
                    </li>

                    <!-- 退出 -->
                    <li class="am-text-sm" @click="logout">
                        <a href="javascript:;">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </header>
</template>

<script>
    export default{
        data(){
            return {
                name: '',
            }
        },

        created() {
            let user = this.getSession(this.KEYS.USER_INFO);
            this.name = user.name;
        },

        methods: {
            logout(){
                this.setSession(this.KEYS.USER_INFO);
                this.$router.push("/");
                window.location.reload();
            },

        },

        components: {}
    }
</script>