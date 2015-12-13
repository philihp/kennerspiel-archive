import fetch from 'isomorphic-fetch';

export const REQUEST_BOARD = 'REQUEST_BOARD';
export const RECEIVE_BOARD = 'RECEIVE_BOARD';
export const MODIFY_MOVES = 'MODIFY_MOVES';
export const REFRESH_MOVES = 'REFRESH_MOVES';

export function modifyMoves(moves) {
  return {
    type: MODIFY_MOVES,
    moves,
  };
}

export function refreshMoves(moves) {
  return {
    type: REFRESH_MOVES,
    moves,
  };
}

function requestBoard(moves) {
  return {
    type: REQUEST_BOARD,
    moves,
  };
}

function receiveBoard(moves, json) {
  return {
    type: RECEIVE_BOARD,
    moves,
    board: json,
    receivedAt: Date.now(),
  };
}

function fetchBoard(moves) {
  return dispatch => {
    dispatch(requestBoard(moves));
    const host = (window.location.hostname === 'kennerspiel.com') ? 'weblabora-svc.herokuapp.com' : 'localhost:5000';
    return fetch(`//${host}/`, {
      method: 'post',
      body: moves,
    })
    .then(req => req.json())
    .then(json => dispatch(receiveBoard(moves, json)));
  };
}

function shouldFetchBoard(state, moves) {
  const board = state.boardByMoves[moves];
  let shouldIt = board.didInvalidate;
  if (!board) {
    shouldIt = true;
  } else if (board.isFetching) {
    shouldIt = false;
  }
  return shouldIt;
}

export function fetchBoardIfNeeded(moves) {
  return (dispatch, getState) => {
    if (shouldFetchBoard(getState(), moves)) {
      return dispatch(fetchBoard(moves));
    }
  };
}
