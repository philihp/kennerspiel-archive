define(function (require, exports, module) {
  var Bootstrap = require('bootstrap'),
      React = require('react'),
      Jquery = require('jquery'),
      Board = require('components/Board');

  var WeblaboraApp = React.createClass({
    render: function() {
      return (
          <Board/>
      );
    },
  });

  module.exports = WeblaboraApp;
});

