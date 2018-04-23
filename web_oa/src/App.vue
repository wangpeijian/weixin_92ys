<template>
    <div id="app">

        <div v-if="isLogin" class="full-height">

            <c-header/>
            <c-menu/>

            <div class="tpl-content-wrapper">
                <router-view></router-view>
            </div>
        </div>
        <c-login v-else v-on:loginSuccess="setLogin"/>

    </div>
</template>

<script>
    import Login from './pages/Login'

    import Header from './comment/Header'
    import Menu from './comment/Menu'

    export default {
        name: 'app',

        data () {
            return {
                isLogin: false
            }
        },

        created: function () {
            //启动时判断用户是否登录

            let user = this.getSession(this.KEYS.USER_INFO);

            if (!user) {
                this.isLogin = false;
            } else {
                this.isLogin = true;
            }

	        this.$router.replace("/");
        },

        methods: {
            setLogin(){
                this.isLogin = true;
            },
        },

        components: {
            'c-login': Login,
            'c-header': Header,
            'c-menu': Menu,
        }
    }

</script>

<style lang="scss" rel="stylesheet/scss">
    /*-------页面样式重置-------*/
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }

    body {
        /*解决iphone点击页面出现黑影的问题*/
        -webkit-tap-highlight-color: rgba(0, 0, 0, 0) !important;
        /*解决页面滚动不流畅问题 -- 只在需要滚动的区域添加*/
        /*-webkit-overflow-scrolling : touch;*/
        /*解决chrome浏览器上font-size最小12px的问题*/
        -webkit-text-size-adjust: none;
        /*设置文字抗锯齿，使页面文字更清晰*/
        -webkit-font-smoothing: antialiased;
        /*禁止选择页面上的文字*/
        /*-webkit-user-select: none;*/
        /*防止iphone无法点击*/
        /*cursor: pointer;*/
        font-family: 微软雅黑, Arial, Microsoft YaHei, 黑体, 宋体, sans-serif !important;
        box-sizing: border-box;

        input {
            -webkit-appearance: none;
        }

        input，textarea {
            outline: none;
        }

        a, abbr, acronym, address, applet, article, aside, audio, b, big, blockquote, body, canvas, caption, center, cite, code, dd, del, details, dfn, div, dl, dt, em, embed, fieldset, figcaption, figure, footer, form, h1, h2, h3, h4, h5, h6, header, html, i, iframe, img, ins, kbd, label, legend, li, mark, menu, nav, object, ol, output, p, pre, q, ruby, s, samp, section, small, span, strike, strong, sub, summary, sup, table, tbody, td, tfoot, th, thead, time, tr, tt, u, ul, var, video {
            margin: 0;
            padding: 0;
            border: 0;
            font: inherit;
            vertical-align: baseline;
            word-wrap: break-word
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;

            thead{

                th{
                    text-align: center;
                }
            }

            tbody{

                td {
                    vertical-align: middle !important;
                    text-align: center;
                }
            }
        }

        button, input, select, textarea {
            outline: 0;
            -webkit-box-sizing: border-box
        }

        article, aside, details, figcaption, figure, footer, header, menu, nav, section {
            display: block
        }

        li, ol, ul {
            list-style: none
        }

        blockquote, q {
            quotes: none
        }

        blockquote:after, blockquote:before, q:after, q:before {
            content: '';
            content: none
        }

        b, strong {
            font-weight: 700
        }

        img {
            max-width: 100%;
            vertical-align: middle;
            border: 0;
            -ms-interpolation-mode: bicubic;
            -webkit-touch-callout: none;
            display: block;
        }

        * {
            outline: none !important;
        }

        a {
            color: #000;
            text-decoration: none;
            -webkit-touch-callout: none;
        }

        a.active {
            color: #000
        }
    }
    /*-------页面样式重置-------*/

    .full-height {
        height: 100%;
    }

    .tpl-content-wrapper{
        color: #ffffff;

        margin-left: 0;
        min-height: inherit;
        position: absolute;
        z-index: 0;
        left: 240px;
        right: 0;
        top: 56px;
        bottom: 0;
        overflow: auto;
        padding: 20px;

        .row-content{
            padding: 0;
        }

        a {
            color: #ffffff;
        }

        a.active {
            color: #ffffff
        }
    }

    .block {
        height: 20px;
        clear: both;
    }

    .form-input {
        background: transparent !important;
        color: #fff !important;
    }

    .btn-group {
        padding: 20px 0;

        .am-btn{
            margin: 0 4px;
        }
    }

    .submit-btn{
        width: 100%;
    }

    table{
        img{
            height: 50px;
            width: 50px;
            border-radius: 4px;
            margin: 0 auto;
        }
    }

    ::-webkit-scrollbar
    {
        width: 8px;
        height: 8px;
        background-color: #282d2f;
    }

    /*定义滚动条轨道 内阴影+圆角*/
    ::-webkit-scrollbar-track
    {
        -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        border-radius: 10px;
        background-color: #282d2f;
    }

    /*定义滑块 内阴影+圆角*/
    ::-webkit-scrollbar-thumb
    {
        border-radius: 10px;
        -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
        background-color: #aaaaaa;
    }
</style>
