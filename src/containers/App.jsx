import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { modifyMoves, fetchBoardIfNeeded, refreshMoves } from '../actions';
import MoveBox from '../components/MoveBox';
import Board from '../components/Board';
import NavMain from '../components/NavMain';

const stubgame = 'config PLAYERS 4\nconfig LENGTH LONG\nconfig COUNTRY IRELAND\nstart\n';

class App extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleRefreshClick = this.handleRefreshClick.bind(this);
  }

  componentDidMount() {
    const { dispatch, moves } = this.props;
    dispatch(fetchBoardIfNeeded(moves));
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.moves !== this.props.moves) {
      const { dispatch, moves } = nextProps;
      dispatch(fetchBoardIfNeeded(moves));
    }
  }

  handleChange(newMoves) {
    this.props.dispatch(modifyMoves(newMoves));
  }

  handleRefreshClick(e) {
    e.preventDefault();

    const { dispatch, moves, isFetching } = this.props;

    if(!isFetching) {
      dispatch(refreshMoves(moves));
      dispatch(fetchBoardIfNeeded(moves));
    }
  }

  render () {
    const { moves, board, isFetching, lastUpdated } = this.props;

    return (
      <div>
        <NavMain activePage="home" />

        <div className="container">

          <div>
            <a href="#" className="btn btn-default" disabled={isFetching} onClick={this.handleRefreshClick}>Refresh</a>
            <span>Last updated at {new Date(lastUpdated).toLocaleTimeString()}</span>
          </div>

          <MoveBox value={stubgame} onChange={this.handleChange} />

          <div style={{ opacity: isFetching ? 0.5 : 1 }}>
            <Board board={board} />
          </div>

        </div>
      </div>
    );
  }
}

App.propTypes = {
  moves: PropTypes.string.isRequired,
  isFetching: PropTypes.bool.isRequired,
  lastUpdated: PropTypes.number,
  dispatch: PropTypes.func.isRequired
};

function mapStateToProps(state) {
  const { moves, boardByMoves } = state;
  const {
    isFetching,
    lastUpdated,
    items: board
    } = boardByMoves[moves] || {
    isFetching: true,
    items: []
  };

  return {
    moves,
    board,
    isFetching,
    lastUpdated
  };
}

export default connect(mapStateToProps)(App);
