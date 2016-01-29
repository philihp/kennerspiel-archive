import React, { PropTypes, Component } from 'react';

import Rondel from './Rondel';
import Tableau from './Tableau';

const propTypes = {
  board: PropTypes.object.isRequired,
};

export default class Board extends Component {
  render() {
    return (
      <div className="board">
        {this.props.board.wheel &&
          (<Rondel wheel={this.props.board.wheel} armOffset={this.props.board.armOffset} />)
        }
        {this.props.board.players && this.props.board.players.map(player =>
          <Tableau player={player} />
        )}
      </div>
    );
  }
}

// <pre>
//   {JSON.stringify(this.props.board)}
// </pre>

Board.propTypes = propTypes;
