import React, { PropTypes, Component } from 'react';

import './Tableau.css';

const propTypes = {
  player: PropTypes.object.isRequired,
};

export default class Tableau extends Component {

  renderCellContents(cell) {
    switch (cell.terrainUse) {
    case 'FOREST':
      return <div className="forest">&#x328D;</div>;
    case 'MOOR':
      return <div className="moor">&#x32AE;</div>;
    case 'BUILDING':
      return <div className="building">{cell.erection.name}</div>;
    case 'EMPTY':
      return null;
    default:
      return cell.terrainUse;
    }
  }

  render() {
    const landscape = this.props.player.landscape.terrainTable;


    return (
      <div className="tableau" key={`tableau-${this.props.player.color}`}>
        <h3>{this.props.player.color}</h3>
        <table className="landscape">
          {landscape.map(row =>
            <tr>
              {row.map(cell => {
                if (cell) {
                  return (
                    <td className={cell.terrainType}>
                      {this.renderCellContents(cell)}
                    </td>
                  );
                }
                return null;
              })}
            </tr>
          )}
        </table>
      </div>
    );
  }
}

Tableau.propTypes = propTypes;
