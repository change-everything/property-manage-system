/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
module.exports = {   
    devServer: {
        proxy: {
            '/api': {
                target: 'http://192.168.0.106:8081/',//接口的前缀
                ws:true,//代理websocked
                changeOrigin:true,//虚拟的站点需要更管origin
                pathRewrite:{
                    '^/api':''//重写路径
                }
            }
        }
    }
}