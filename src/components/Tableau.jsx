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

  renderChits(chit) {
    const chits = [];
    for (let i = 0; i < chit.quantity; i++) {
      chits.push(
        <div key={`inventory.${this.props.player.color}.${chit.type}.${i}`} className="chit">{chit.type}</div>
      );
    }

    return chits;
  }

  render() {
    const landscape = this.props.player.landscape.terrainTable;
    const inventory = this.props.player.inventory;
    const color = this.props.player.color;

    return (
      <div className="tableau" key={`tableau-${color}`}>
        <h3>{color}</h3>
        {inventory.map((chit) =>
          this.renderChits(chit)
        )}
        <table className="landscape">
          <tbody>
            {landscape.map((row, y) =>
              <tr key={`landscape.${color}.${y}`}>
                {row.map((cell, x) => {
                  if (cell) {
                    return (
                      <td key={`landscape.${color}.${y}.${x}`}
                          className={cell.terrainType}>
                        {this.renderCellContents(cell)}
                      </td>
                    );
                  }
                  return null;
                })}
              </tr>
            )}
          </tbody>
        </table>
      </div>
    );
  }
}

Tableau.propTypes = propTypes;
