/**
 * Created by phil_busby on 15-06-19.
 */
define(function (require, exports, module) {
  var Bootstrap = require('bootstrap'),
      React = require('react'),
      Jquery = require('jquery');

  var WeblaboraApp = React.createClass({
    render: function() {
      return (
          <div>
            Weblabora
          </div>
      );
    },
  });

  module.exports = WeblaboraApp;
  // with CommonJS, you could also just return WeblaboraApp, but that makes things less like Node.
});

