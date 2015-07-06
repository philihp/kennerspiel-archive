'use strict'

define(function (require, exports, module) {
  var React = require('react');
  var BoardActions = require('actions/BoardActions');

  return React.createClass({
    displayName: 'Extensions',

    render: function() {
      var costs = [];
      for(var i = 0; i < this.props.costs.length; i++) {
        costs.unshift(this.props.costs[i]);
        costs.unshift(', ');
      }
      costs.shift();
      return (
          <div>
            <button className="btn btn-primary" onClick={this._buy}>Buy {this.props.type}</button> Costs: {costs}
          </div>
      );
    },

    _buy: function() {
      BoardActions.addCommand(this.props.command);
    }

  });
});
