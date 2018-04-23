/**
 * Created by peijian.wang on 2016/5/5.
 */
/**
 * 扩展一些js方法，默认加载时执行
 * */

const APIUrl = "http://localhost:8081/app/";

const companyInfo = {
	name: "92出品",
	phone: "15001085992",
	phoneLabel: "150-010-85992",
}

const listener = function(evt) {
	if(!evt._isScroller) {
		evt.preventDefault();
	}
}

export default class WindowExtend {

    constructor(){
	
	    //扩展时间对象
        (function () {
		        // 对Date的扩展，将 Date 转化为指定格式的String
		        // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
		        // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
		        // 例子：
		        // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
		        // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
		        //支持时间格式化
		        Date.prototype.Format = function (fmt) { //author: meizz
			        var o = {
				        "M+": this.getMonth() + 1, //月份
				        "d+": this.getDate(), //日
				        "h+": this.getHours(), //小时
				        "m+": this.getMinutes(), //分
				        "s+": this.getSeconds(), //秒
				        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
				        "S": this.getMilliseconds() //毫秒
			        };
			        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			        for (var k in o)
				        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			        return fmt;
		        }
	        })();

	    //添加响应式控制代码
	    (function (doc, win) {
		    var docEl = doc.documentElement,
			    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
			    recalc = function () {
				    var clientWidth = docEl.clientWidth;
				    if (!clientWidth) return;
				    docEl.style.fontSize = 20 * (clientWidth / 320) + 'px';
			    };
		    if (!doc.addEventListener) return;
		    win.addEventListener(resizeEvt, recalc, false);
		    doc.addEventListener('DOMContentLoaded', recalc, false);
	    })(document, window);

    }
	
	
	getUserLocation(cb) {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function (pos) {
				cb(pos.coords.latitude, pos.coords.longitude);
			}, function (error) {
				cb("", "");
				
				switch (error.code) {
					case error.PERMISSION_DENIED:
						break;
					case error.POSITION_UNAVAILABLE:
						break;
					case error.TIMEOUT:
						break;
					case error.UNKNOWN_ERROR:
						break;
				}
			}, {
				enableHighAccuracy: true, // 是否获取高精度结果
				timeout: 5000, //超时,毫秒
				maximumAge: 0 //可以接受多少毫秒的缓存位置
			});
		} else {
			cb("", "");
		}
	}
	
	configJsSDK(jsApiList, cb) {
		this.post(`/wechat/jsSDK`, {
			url: encodeURIComponent(window.location.href),
		}).then((res)=>{
			if (!!cb) {
				wx.ready(cb);
			}
			wx.error(function (res) {
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里
				// alert("调用微信js失败" + JSON.stringify(res));
			});
			wx.config({
				debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印
				appId: res.appId, // 必填，公众号的唯一标识
				timestamp: res.timestamp + "", // 必填，生成签名的时间戳
				nonceStr: res.nonceStr, // 必填，生成签名的随机串
				signature: res.signature,// 必填，签名，见附录1
				jsApiList: jsApiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
		})
	}
	
	get(url){
		return fetch(APIUrl + url, {
			method: 'get',
			headers: {"Content-Type": "application/json"},
		}).then(function (response) {
			return response.json();
		}).then(function (res) {
			console.log(url + "：", JSON.parse(JSON.stringify(res)));
			return res;
		}).catch(function (e) {
			console.error(e);
		})
	}
	
	post(url, data = {}){
		return fetch(APIUrl + url, {
			method: 'post',
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(data),
		}).then(function (response) {
			return response.json();
		}).then(function (res) {
			console.log(url + "：",data , JSON.parse(JSON.stringify(res)));
			return res;
		}).catch(function (e) {
			console.error(e);
		})
	}
	
	/*
	 * 直接上传base64文件
	 * */
	postBase64(data = {}){
		if(!data.imgUrl){
			return Promise.resolve("");
		}
		
		//判断是否需要先上传封面图片
		if (data.imgUrl.indexOf("http") == -1) {
			//base64编码文件
			return this.post(`helper/postBase64`, {
				scoped: data.scoped,
				base64: data.imgUrl,
				fileName: data.fileName,
			}).then(res => {
				return res.data.imgUrl;
			});
			
		} else {
			return Promise.resolve(data.imgUrl);
		}
	}
	
	KEYS = {
		//记录用户登录信息
		"USER_INFO": "USER_INFO",
		//记录用户选择的产品配置信息
		"PRODUCT_ORDER_INFO": "PRODUCT_ORDER_INFO",
	}
	
	//轮播图控件配置
	getCarouselConfig = {
		dots: true,
		infinite: true,
		speed: 500,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 5000,
		fade: false,
		draggable: false,
		pauseOnHover: true,
		arrows: false,
	}

    //设置页面title
    setPageTitle(title) {
        document.getElementsByTagName("title")[0].innerText = title;
	
        //if(!!navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)){
	        // hack在微信等webview中无法修改document.title的情况
	        var body = document.getElementsByTagName("body")[0];
	        var iframe = document.createElement("iframe");
	        iframe.style.display = "none";
	        //TODO 如果出现ios无法更新title则此处添加一个可以访问的链接
	        iframe.src = '';
		    iframe.addEventListener('load', function(){
			    setTimeout(function() {
				    iframe.removeEventListener('load', function(){
					    body.removeChild(iframe);
				    });
			    }, 0)
			})
	
	        body.appendChild(iframe);
        //}
    }
    
    getCompanyInfo(){
		return companyInfo;
    }
	
	getSession(key){
		let value = window.sessionStorage.getItem(key);
		
		try {
			value = JSON.parse(value);
		} catch (e) {
			console.info(e, key, value);
			value = {};
		}
		
		if(!value){
			return {};
		}
		
		return value;
	}
	
	setSession(key, value){
		if (value === undefined) {
			window.sessionStorage.removeItem(key);
		} else {
			
			if (typeof value === "object") {
				value = JSON.stringify(value)
			}
			
			window.sessionStorage.setItem(key, value);
		}
	}
	
	/**
	 * ios绝对定位滚动bug修复
	 * 2016-12-30
	 * 王佩剑
	 */
	preventPageScroll(className = ""){
		
		document.removeEventListener("touchmove", listener);
		
		if(className == ""){
			console.log("stop")
			return;
		}
		
		document.addEventListener('touchmove', listener);
		
		let els = className;
		
		if(typeof className == "string"){
			els = [className];
		}
		
		for(let i in els){
			
			let el = document.querySelector(els[i]);
			
			if(!!el){
				el.addEventListener('touchstart', function() {
					
					let top = el.scrollTop
						,totalScroll = el.scrollHeight
						,currentScroll = top + el.offsetHeight;
					if(top === 0) {
						el.scrollTop = 1;
					}else if(currentScroll === totalScroll) {
						el.scrollTop = top - 1;
					}
				});
				
				el.addEventListener('touchmove', function(evt) {
					if(el.offsetHeight < el.scrollHeight)
						evt._isScroller = true;
				});
			}
			
		}
		
	}
}
