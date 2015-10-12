import React, { PropTypes, Component } from 'react';

export default class Board extends Component {
  render () {
    return (
      <pre>
        {JSON.stringify(this.props.board)}
      </pre>
    );
  }
}

Board.propTypes = {
  board: PropTypes.array.isRequired
};
