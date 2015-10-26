import React, { PropTypes, Component } from 'react';

import Rondel from './Rondel'

export default class Board extends Component {
  render () {
    return (
      <div className="board">
        {this.props.board &&
          (<Rondel wheel={this.props.board.wheel} armOffset={this.props.board.armOffset} />)
        }
        <pre>
          {JSON.stringify(this.props.board)}
        </pre>
      </div>
    );
  }
}
