<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
  <router-view v-if="isRouterAlive" />
</template>

<script>
export default {
  name: 'App',
  components: {},
  data(){
    return{
      isRouterAlive:true
    }
  },
  provide(){
    return{
       reload : this.reload
    }
  },
  methods:{
    reload () {
      this.isRouterAlive = false
      this.$nextTick(function() {
         this.isRouterAlive = true
      })
    }
  }
}
</script>

<style>
#app {
  padding:0;            /*清除内边距*/
  margin:0;              /*清除外边距*/
}
</style>
