const webpack = require('webpack');
const WebpackDevServer = require('webpack-dev-server');
const config = require('./webpack.config');

new WebpackDevServer(webpack(config), {
    publicPath: config.output.publicPath,
    hot: true,
    historyApiFallback: true,
}).listen((process.env.PORT || 5000), 'localhost', function (err, result) {
    if (err) {
        console.log(err)
    }

    console.log('Listening at localhost:3000')
});