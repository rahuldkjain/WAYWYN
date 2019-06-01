module.exports= {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://10.177.7.134:8080',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}