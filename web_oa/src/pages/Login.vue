<style lang="scss" rel="stylesheet/scss" scoped>

</style>

<template>
    <div class="am-g tpl-g">
        <div class="tpl-login">
            <div class="tpl-login-content">
                <div class="tpl-login-logo"></div>

                <form class="am-form tpl-form-line-form">
                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" v-model="username" placeholder="请输入账号">

                    </div>

                    <div class="am-form-group">
                        <input type="password" class="tpl-form-input" v-model="password" placeholder="请输入密码">

                    </div>

                    <div class="am-alert am-alert-danger" data-am-alert v-if="!!errorTip">
                        {{errorTip}}
                    </div>

                    <br v-if="!errorTip">

                    <div class="am-form-group">

                        <button type="button"
                                class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn"
                                @click="submit">提交
                        </button>

                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      password: '',
      errorTip: '',
    }
  },

   methods: {
        submit(){
            this.post("user/oaLogin", {
            	username: this.username,
                password: this.password,
            }).then(res => {
                if(res.code !== 0){
                    this.errorTip = res.msg;
                }else{
                    this.errorTip = "";

                    this.setSession(this.KEYS.USER_INFO, res.data);

                    this.$emit('loginSuccess');
                }
            })
        }
    }
}


</script>
