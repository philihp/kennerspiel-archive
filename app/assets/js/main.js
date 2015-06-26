(function (requirejs) {
  "use strict";
  requirejs.config({
    baseUrl: "/assets/js",
    shim: {
      "jquery": {
        exports: "$"
      },
      "bootstrap" : {
        deps :['jquery']
      }
      //"jsRoutes": {
      //  exports: "jsRoutes"
      //}
    },
    paths: {
      jquery: "../lib/jquery/dist/jquery",
      bootstrap: "../lib/bootstrap/dist/js/bootstrap",
      react: "../lib/react/react",
      alt: "../lib/alt/dist/alt"
    }
  });

  requirejs.onError = function (err) {
    console.log(err);
  };

  require(['./app']);
})(requirejs);