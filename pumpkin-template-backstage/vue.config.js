module.exports = {

    chainWebpack: config => {
        config.plugin('html').tap(args => {
            args[0].title = 'Pumpkin后台管理系统';
            return args;
        })
    },

    devServer: {
        port: 8888,
    }

}
