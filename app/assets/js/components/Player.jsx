'use strict'

define(function (require, exports, module) {
  var React = require('react');
  var Erection = require('components/Erection');

  return React.createClass({
    displayName: 'Player',

    render: function () {
      var rows = [];
      for(var i = 0; i < this.props.landscape.length; i++) {
        var cells = [];
        for(var j = 0; j < this.props.landscape[i].length; j++) {
          var plot = this.props.landscape[i][j];
          cells.push(
              <td key={j} className={"terrain-cell " + plot.terrainType.toLowerCase()}>
                <Erection terrainUse={plot.terrainUse} building={plot.erection} />
              </td>
          );
        }
        rows.push(<tr key={i}>{cells}</tr>);
      }

      return (
          <div>
            <h3>{this.props.color}</h3>
            <table className="table terrain-table">
              {rows}
            </table>
          </div>
      );
    }

  });
});