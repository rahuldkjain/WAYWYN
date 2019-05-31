module.exports = {
    devServer:{
        proxy:{
            '/api': {
                target: "http://10.177.7.144:8080/",
                changeOrigin:true,
                pathRewrite: {
                    '^/api': ''
                }	
            }   
         }
    }
}