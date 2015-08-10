'use strict'

define(function (require, exports, module) {
  var React = require('react');
  var Erection = require('components/Erection');

  return React.createClass({
    displayName: 'Player',

    render: function () {
      var rows = [];
      for (var row in this.props.landscape) {
        var cells = [];
        for (var col in this.props.landscape[row]) {
          var plot = this.props.landscape[row][col];
          if (plot != null) {
            cells.push(
                <td key={'('+col+','+row+')'} className={"terrain-cell " + plot.terrainType.toLowerCase()}>
                  <Erection terrainUse={plot.terrainUse} building={plot.erection}/>
                </td>
            );
          }
          else {
            // if you don't do this, then weird stuff happens with buying districts and plots
            cells.push(
                <td key={'('+col+','+row+')'} className="hide" />
            )
          }
        }
        rows.push(<tr key={'('+row+')'}>{cells}</tr>);
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
