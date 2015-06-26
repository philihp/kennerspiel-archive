define(function (require, exports, module) {
  var alt = require('../alt');
  var BoardActions = require('../actions/BoardActions');

  var BoardStore = alt.createStore({
    displayName: 'BoardStore',
    bindListeners: {
      handleUpdateBoard: BoardActions.updateBoard
    },
    state: {
      board: null
    },
    publicMethods: {
    },
    handleUpdateBoard: function(board) {
      this.board = board;
    }
  });

  module.exports = BoardStore;
});