(function (requirejs) {
  "use strict";

  requirejs.config({
    baseUrl: "/assets/js",
    shim: {
      "jquery": {
        exports: "$"
      }
      //"jsRoutes": {
      //  exports: "jsRoutes"
      //}
    },
    paths: {
      "math": "lib/math",
      // Map the dependencies to WebJars directly
      "jquery": "/assets/lib/jquery/jquery",
      "bootstrap": "/assets/lib/bootstrap/dist/js/bootstrap.min",
      "react": "/assets/lib/react/react"
    }
  });

  requirejs.onError = function (err) {
    console.log(err);
  };
})(requirejs);