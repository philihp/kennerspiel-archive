'use strict'

define(function (require, exports, module) {
  var React = require('react');

  return React.createClass({
    displayName: 'Rondel',

    render: function() {
      var headers = [];
      var cells = [];
      for(var i = 0; i < this.props.armValues.length; i++) {
        headers.push(<th key={i}>{this.props.armValues[i]}</th>);
        var tokens = [];
        for(var j = 0; j < this.props.table[i].length; j++) {
          tokens.push(<div key={j}>{this.props.table[i][j]}</div>);
        }
        cells.push(<td key={i}>{tokens}</td>);
      }

      return (
        <div>
          <table className="table">
            <tr>
              {headers}
            </tr>
            <tr>
              {cells}
            </tr>
          </table>
        </div>
      );
    }

  });
});