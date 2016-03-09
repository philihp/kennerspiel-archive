import React, { PropTypes, Component } from 'react';

import './Tableau.css';
import Forest from './Forest';

const propTypes = {
  player: PropTypes.object.isRequired,
};

export default class Tableau extends Component {

  renderCellContents(cell) {
    switch (cell.terrainUse) {
    case 'FOREST':
      return <Forest/>;
    case 'MOOR':
      return <div className="moor">&#x32AE;</div>;
    case 'BUILDING':
      return (
        <img className="building" src={`/images/weblabora/buildings/${cell.erection.image}.jpg`} />
      );
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
        <img key={`${chit.type}-${i}`} className="chit" src={`/images/weblabora/chits/${chit.type}.jpg`} />
      );
    }
    return chits;
  }

  render() {
    const landscape = this.props.player.landscape.terrainTable;
    const inventory = this.props.player.inventory;
    const color = this.props.player.color;

    return (
      <div className="tableau">
        <h3>{color}</h3>
        {inventory.map((chit) =>
          this.renderChits(chit)
        )}
        <table className="landscape">
          <tbody>
            {landscape.map((row, y) =>
              <tr key={y}>
                {row.map((cell, x) => {
                  if (cell) {
                    return (
                      <td key={x} className={cell.terrainType}>
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
