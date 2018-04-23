/**
 * Created by Administrator on 2017/6/21 0021.
 */
import Paging from '../comment/Paging'
import UploadImage from '../comment/UploadImage'
import MultipleFileUpload from '../comment/MultipleFileUpload'


//保存所有的广播事件
window.EVENT_STORAGE = window.EVENT_STORAGE === undefined ? {} : window.EVENT_STORAGE;

/*后台接口地址*/
const INTERFACE = "http://localhost:8081/oa/";

export default class {

    constructor() {

    }

    install(Vue, options) {
        this.installExtendsFunction();

        this.installComponent(Vue);

        this.installPrototype(Vue);

        this.installFilter(Vue);
    }

    /*------------------安装扩展方法------------------*/
    installExtendsFunction() {
        /**
         * 扩展时间对象，增加Format方法
         */
        (function() {
            /*对Date的扩展，将 Date 转化为指定格式的String
             月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
             年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
             例子：
             (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
             (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
             支持时间格式化*/
            Date.prototype.Format = function(fmt) { //author: meizz
                let o = {
                    "M+": this.getMonth() + 1, //月份
                    "d+": this.getDate(), //日
                    "h+": this.getHours(), //小时
                    "m+": this.getMinutes(), //分
                    "s+": this.getSeconds(), //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds() //毫秒
                };
                if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (let k in o)
                    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            }
        }())
    }

    /*------------------添加vue组件------------------*/
    installComponent(Vue) {
        Vue.component("c-paging", Paging);
        Vue.component("c-upload-image", UploadImage);
        Vue.component("c-multiple-file-upload", MultipleFileUpload);
    }

    /*------------------添加vue过滤器------------------*/
    installFilter(Vue) {
        /**
         * 格式化日期的过滤器
         * (yyyy-MM-dd)
         */
        Vue.filter('date-filter', function(time) {
            return new Date(time).Format("yyyy-MM-dd");
        });

        /**
         * 格式化日期+时间的过滤器
         * (yyyy-MM-dd hh:mm)
         */
        Vue.filter('date-time-filter', function(time) {
            return new Date(time).Format("yyyy-MM-dd hh:mm");
        });

        Vue.filter('time-format', function(value){
            if(!!value){
                return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
            }
        });
    }

    /*------------------添加vue实例方法------------------*/
    installPrototype(Vue) {
        //定义storage里面的key
        Vue.prototype.KEYS = {
            //记录当前登录的用户信息
            USER_INFO: 'USER_INFO',
        };

        //定义全局事件
        Vue.prototype.EVENTS = {

        };

        /**
         * 获取session中的对象
         * @param key
         */
        Vue.prototype.getSession = key => {
            let value = window.sessionStorage.getItem(key);

            try {
                value = JSON.parse(value);
            } catch (e) {
                console.error(e, key, value);
                return null;
            }

            return value;
        };

        /**
         * 设置、清空session中的对象
         * @param key
         * @param value
         */
        Vue.prototype.setSession = (key, value) => {
            if (value === undefined) {
                window.sessionStorage.removeItem(key);
            } else {

                if (typeof value === "object") {
                    value = JSON.stringify(value)
                }

                window.sessionStorage.setItem(key, value);
            }
        };

        /**
         * 获取storage中的对象
         * @param key
         */
        Vue.prototype.getStorage = key => {
            let value = window.localStorage.getItem(key);

            try {
                value = JSON.parse(value);
            } catch (e) {
                console.error(e, key, value);
                return null;
            }

            return value;
        };

        /**
         * 设置、清空Storage中的对象
         * @param key
         * @param value
         */
        Vue.prototype.setStorage = (key, value) => {
            if (value === undefined) {
                window.localStorage.removeItem(key);
            } else {

                if (typeof value === "object") {
                    value = JSON.stringify(value)
                }

                window.localStorage.setItem(key, value);
            }
        };

        /**
         * get请求
         * @param url
         * @returns {Promise.<TResult>}
         */
        Vue.prototype.get = url => {
            return fetch(INTERFACE + url, {
                method: 'get',
                headers: {"Content-Type": "application/json"},
            }).then(function (response) {
                return response.json();
            }).then(function (res) {
                console.info(url + "：", JSON.parse(JSON.stringify(res)));
                return res;
            }).catch(function (e) {
                console.error(e);
            })
        };

        /**
         * post请求
         * @param url
         * @param data
         * @returns {Promise.<TResult>}
         */
        Vue.prototype.post = (url, data = {}) => {
            return fetch(INTERFACE + url, {
                method: 'post',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(data),
            }).then(function (response) {
                return response.json();
            }).then(function (res) {
                console.info(url + "：", data, JSON.parse(JSON.stringify(res)));
                return res;
            }).catch(function (e) {
                console.error(e);
            })
        };

        /**
         * 删除操作时的弹出框
         * 封装element的confirm弹出层
         */
        Vue.prototype.deleteConfirm = function(cb, msg = "确认删除？") {
            this.$confirm(msg, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                cb();
            }).catch(() => {

            });
        };

        /**
         * 封装操作的错误提示信息
         * @param msg
         */
        Vue.prototype.showErrorTip = function(msg) {
            this.$message.error({
                message: msg,
                customClass: 'form-error',
                showClose: true,
                duration: 2000,
            });
        };

        /**
         * 封装展示弹出消息
         * @param msg
         * @param type
         */
        Vue.prototype.showMsgTip = function(msg, type = 'success') {
            this.$message({
                message: msg,
                type: type,
                customClass: 'form-error',
                showClose: true,
                duration: 2000,
            });
        };

        /**
         * 新增一条订阅记录（用于组件间通信）
         *
         * @param eventName
         * @param owner
         * @param cb
         */
        Vue.prototype.subscribe = (eventName, owner, cb) => {
            let queue = window.EVENT_STORAGE[eventName];

            if (queue === undefined) {
                queue = [];
            }

            queue.push({
                owner: owner,
                cb: cb,
            });

            window.EVENT_STORAGE[eventName] = queue;
        };

        /**
         * 订阅者取消订阅消息（用于组件间通信）
         *
         * @param eventName
         * @param owner
         * @returns {boolean}
         */
        Vue.prototype.unsubscribe = (eventName, owner) => {
            let queue = window.EVENT_STORAGE[eventName];

            if (queue === undefined) {
                console.error(`取消订阅事件失败：${eventName},事件不存在`);
                return false;
            }

            window.EVENT_STORAGE[eventName] = queue.filter(obj => {
                return obj.owner !== owner;
            });
        };

        /**
         * 推送一条全局消息（用于组件间通信）
         *
         * @param eventName
         * @param sender
         * @param parameter
         * @param cb
         */
        Vue.prototype.broadcast = (eventName, sender, parameter, cb) => {
            let queue = window.EVENT_STORAGE[eventName];

            if (queue === undefined　 || queue.length === 0) {
                return false;
            }

            queue.map(obj => {
                obj.cb(parameter);
            });

            if (cb) {
                cb();
            }
        };

        /**
         * 下载一个文件
         *
         * @param url
         */
        Vue.prototype.downloadFile = (url) => {
            let anchor = document.createElement("a");
            anchor.href = url;
            anchor.setAttribute("download", "");
            anchor.style.display = "none";
            document.body.appendChild(anchor);
            setTimeout(function() {
                anchor.click();
                document.body.removeChild(anchor);
            }, 66);
        };

        /**
         * 直接上传base64文件
         */
        Vue.prototype.postBase64 = (data = {}) =>{
            if(!data.imgUrl){
                return Promise.resolve("");
            }

            //判断是否需要先上传封面图片
            if (data.imgUrl.indexOf("http") === -1) {
                //base64编码文件
                return Vue.prototype.post(`helper/postBase64`, {
                    scoped: data.scoped,
                    base64: data.imgUrl,
                    fileName: data.fileName,
                }).then(res => {
                    return res.data.imgUrl;
                });

            } else {
                return Promise.resolve(data.imgUrl);
            }
        };

        /**
         * 富文本编辑器配置
         */
        Vue.prototype.froalaEditorConfig = (id = "editor") => {
            $('#' + id).editable({
                placeholderText: '',
                minHeight: 500,
                inlineMode: false,
                alwaysBlank: true,
                language: "zh_cn",
                allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
                //saveURL: 'hander/FroalaHandler.ashx',
                saveParams: {},
                imageUploadURL: INTERFACE + 'helper/postImage',
                imageDeleteURL: INTERFACE + 'helper/removeImage',//删除图片
                imageParams: {},
                enableScript: false
            }).on('editable.afterRemoveImage', function (e, editor, $img) {
                // Set the image source to the image delete params.
                editor.options.imageDeleteParams = {src: $img.attr('src')};
                // Make the delete request
                editor.deleteImage($img);
            });
        };

        /**
         * 获取编辑器内容
         */
        Vue.prototype.editorGetContent = () => {
            return $("#editor .froala-element").html();
        };

        /**
         * 设置编辑器内容
         */
        Vue.prototype.editorSetContent = (html) => {
            return $("#editor .froala-element").html(html);
        };
    }
}