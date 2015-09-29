const path = require('path');
const webpack = require('webpack');

const mainPath = path.resolve(__dirname, 'src', 'index.js');
const buildPath = path.resolve(__dirname, 'public', 'build');
const nodeModulesPath = path.resolve(__dirname, 'node_modules');

module.exports = {
    devtool: 'eval',
    entry: [
        'webpack-dev-server/client?http://localhost:3003',
        'webpack/hot/only-dev-server',
        mainPath,
    ],
    output: {
        path: buildPath,
        filename: 'bundle.js',
        publicPath: '/build/',
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
    ],
    resolve: {
        extensions: ['', '.js', '.jsx'],
    },
    module: {
        loaders: [{
            test: /\.jsx?$/,
            loaders: ['react-hot', 'babel-loader?stage=0'],
            exclude: [nodeModulesPath],
        },{
            test: /\.css$/,
            loader: 'style!css',
        }]
    },
};