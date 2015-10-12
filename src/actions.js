import fetch from 'isomorphic-fetch';

export const REQUEST_BOARD = 'REQUEST_BOARD';
export const RECEIVE_BOARD = 'RECEIVE_BOARD';
export const MODIFY_MOVES = 'MODIFY_MOVES';
export const REFRESH_MOVES = 'REFRESH_MOVES';

export function modifyMoves(moves) {
  return {
    type: MODIFY_MOVES,
    moves
  };
}

export function refreshMoves(moves) {
  return {
    type: REFRESH_MOVES,
    moves
  };
}

function requestBoard(moves) {
  return {
    type: REQUEST_BOARD,
    moves
  };
}

function receiveBoard(moves, json) {
  return {
    type: RECEIVE_BOARD,
    moves,
    board: json,
    receivedAt: Date.now()
  };
}

function fetchBoard(moves) {
  return dispatch => {
    dispatch(requestBoard(moves));
    return fetch('//weblabora-svc.herokuapp.com/', {
        method: 'post',
        body: moves
      })
      .then(req => req.json())
      .then(json => dispatch(receiveBoard(moves, json)));
  }
}

function shouldFetchBoard(state, moves) {
  const board = state.boardByMoves[moves];
  if (!board) {
    return true;
  } else if (board.isFetching) {
    return false;
  } else {
    return board.didInvalidate;
  }
}

export function fetchBoardIfNeeded(moves) {
  return (dispatch, getState) => {
    if (shouldFetchBoard(getState(), moves)) {
      return dispatch(fetchBoard(moves));
    }
  };
}
X