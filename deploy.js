// This should only run on production, during an npm install.
// When this runs, the bundle.js file will be built and dropped in /public/build/, and served
// as a static asset, rather than on development where it is served with the hot-reloader hotness.
//
// Thanks to http://www.christianalfoni.com/articles/2015_04_19_The-ultimate-webpack-setup for this
if (process.env.NODE_ENV === 'production') {
    var child_process = require('child_process');
    child_process.exec("webpack -p --config webpack.production.config.js", function (error, stdout, stderr) {
        console.log('stdout: ' + stdout);
        console.log('stderr: ' + stderr);
        if (error !== null) {
            console.log('exec error: ' + error);
        }
    });
}