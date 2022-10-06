<template>
<div>
	<el-button @click="showDialog" style="width:360px;height:50px" type="success">人脸登录</el-button>
	<el-dialog v-model="dialogVisable" width="30vw" title="人脸登录"  @close="beforColse">
		<div class="space">
			<video
				id="videoCamera"
				:width="videoWidth"
				:height="videoHeight"
				autoplay
				class="face"
			></video>
			<canvas
				style="display: none"
				id="canvasCamera"
				:width="videoWidth"
				:height="videoHeight"
			></canvas>
			<!-- <div v-if="imgSrc" class="img_bg_camera">
				<p>效果预览</p>
				<img :src="imgSrc" alt class="tx_img" />
			</div> -->
		</div>
		<div>
				提示：
				<span>保持人脸在屏幕中间</span>
		</div>
</el-dialog>
</div>
</template>
<script>
import publicApi from '@/api/publicApi.js'
import {ElNotification} from 'element-plus'
export default {
    name:'loginFace',
  data() {
    return {
      videoWidth: 350,
      videoHeight: 350,
      imgSrc: "",
      thisCancas: null,
    thisContext: null,
		thisVideo: null,
		openVideo: false,
		dialogVisable:false
    };
  },
  mounted() {
    
  },
  methods: {
	showDialog(){
		this.dialogVisable = true;
		setTimeout(() => {
			this.getCompetence()//进入页面就调用摄像头
			//自动抓拍
			this.timer = setInterval(this.setImage,2000)
		}, 1000);
	},

    // 调用权限（打开摄像头功能）
    getCompetence() {
      var _this = this;
      _this.thisCancas = document.getElementById("canvasCamera");
      _this.thisContext = this.thisCancas.getContext("2d");
      _this.thisVideo = document.getElementById("videoCamera");
      _this.thisVideo.style.display = "block";
      // 获取媒体属性，旧版本浏览器可能不支持mediaDevices，我们首先设置一个空对象
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {};
      }
      // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
      // 使用getUserMedia，因为它会覆盖现有的属性。
      // 这里，如果缺少getUserMedia属性，就添加它。
      if (navigator.mediaDevices.getUserMedia === undefined) {
        navigator.mediaDevices.getUserMedia = function (constraints) {
          // 首先获取现存的getUserMedia(如果存在)
          var getUserMedia =
            navigator.webkitGetUserMedia ||
            navigator.mozGetUserMedia ||
            navigator.getUserMedia;
          // 有些浏览器不支持，会返回错误信息
          // 保持接口一致
          if (!getUserMedia) {
            //不存在则报错
            return Promise.reject(
              new Error("getUserMedia is not implemented in this browser")
            );
          }
          // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
          return new Promise(function (resolve, reject) {
            getUserMedia.call(navigator, constraints, resolve, reject);
          });
        };
      }
      var constraints = {
        audio: false,
        video: {
          width: this.videoWidth,
          height: this.videoHeight,
          transform: "scaleX(-1)",
        },
      };
      navigator.mediaDevices
        .getUserMedia(constraints)
        .then(function (stream) {
          // 旧的浏览器可能没有srcObject
          if ("srcObject" in _this.thisVideo) {
            _this.thisVideo.srcObject = stream;
          } else {
            // 避免在新的浏览器中使用它，因为它正在被弃用。
            _this.thisVideo.src = window.URL.createObjectURL(stream);
          }
          _this.thisVideo.onloadedmetadata = function (e) {
			console.log(e)
            _this.thisVideo.play();
          };
        })
        .catch((err) => {
          console.log(err);
        });
    //   alert("打开摄像头");
    },
 
 loginFace(img){
    publicApi.loginFace(img).then((res)=>{
        res =res.data;
        if(res.success == true){
            // console.log(res)
            this.stopNavigator();
            clearInterval(this.timer);
            let data = {
                userInfo:{
                userId:res.data.empId,
                userName:res.data.empName,
                userNum:res.data.empNum,
                role:res.data.role
                },
                permCode:res.data.permCode
            }
                            
            //用vuex存储用户信息,token信息,menu信息
            this.$store.dispatch('users/LoginSuccess',data)
            this.$store.dispatch('users/SaveToken',res.data.token)
            //用session存储token                                    
            sessionStorage.setItem('setToken',JSON.stringify(res.data.token))

            ElNotification({
                message: '登录成功',
                type: 'success',
                duration:'1000',
                showClose:false
            })

            this.$router.push('/')
        }else{
            if(res.statusCode === 2003 || res.statusCode === 2007){
                ElNotification({
                message:'账号不存在',
                type: 'error',
                duration:'1500',
                showClose:false
                })
            }
        }
    })
  },

    //  绘制图片（拍照功能）
    setImage() {
      var _this = this;
      // canvas画图
      _this.thisContext.drawImage(
        _this.thisVideo,
        0,
        0,
        _this.videoWidth,
        _this.videoHeight
      );
      // 获取图片base64链接
      var image = this.thisCancas.toDataURL("image/png");
      _this.imgSrc = image; //赋值并预览图片
      this.loginFace(image);

	// console.log(_this.imgSrc);
    //   alert("拍照");
    },

    // 关闭摄像头
    stopNavigator() {
      this.thisVideo.srcObject.getTracks()[0].stop();
    //   alert("关闭摄像头");
    },

    beforColse(){
      clearInterval(this.timer);
      this.stopNavigator();
    }
  },

  beforeUnmount(){
      clearInterval(this.timer);
      this.stopNavigator();
  }
}
</script>

<style scoped>
.face{
	border-radius: 50%;
}

.space{
	display:flex;
	justify-content: center; 
	align-items:center;
}
</style>