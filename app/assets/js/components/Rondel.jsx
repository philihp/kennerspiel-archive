'use strict'

define(function (require, exports, module) {
  var React = require('react');

  return React.createClass({
    displayName: 'Rondel',

    render: function() {
      return (
        <div>Rondel: {this.props.armValues}</div>
      );
    }

  });
});