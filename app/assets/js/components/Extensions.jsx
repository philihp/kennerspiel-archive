'use strict'

define(function (require, exports, module) {
  var React = require('react');

  return React.createClass({
    displayName: 'Extensions',
    render: function() {
      var costs = [];
      for(var i = 0; i < this.props.costs.length; i++) {
        costs.unshift(this.props.costs[i]);
        costs.unshift(', ');
      }
      costs.shift();
      return (<div>{this.props.type}: {costs}</div>);
    }

  });
});