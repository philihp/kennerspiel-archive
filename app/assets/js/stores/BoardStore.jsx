define(function (require, exports, module) {
  var alt = require('../alt');
  var BoardActions = require('../actions/BoardActions');

  class BoardStore {
    constructor() {
      this.board = null;

      this.bindListeners({
        handleUpdateBoards: BoardActions.UPDATE_BOARD
      });
    }

    handleUpdateBoard(board) {
      this.board = board;
    }
  }

  module.exports = alt.createStore(BoardStore, 'BoardStore');
});