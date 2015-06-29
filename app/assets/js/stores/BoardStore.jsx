define(function (require, exports, module) {
  var alt = require('../alt');
  var BoardActions = require('../actions/BoardActions');
  return alt.createStore({
    displayName: 'BoardStore',

    bindListeners: {
      handleUpdateBoard: BoardActions.updateBoard,
      handleFetchBoard: BoardActions.fetchBoard,
      handleFetchFailed: BoardActions.fetchFailed
    },

    state: {
      board: null,
      errorMessage: null
    },

    publicMethods: {
    },

    handleUpdateBoard: function(board) {
      this.state.board = board;
      this.state.errorMessage = null;
    },

    handleFetchBoard: function() {
      this.state.board = null;
    },

    handleFetchFailed: function(errorMessage) {
      this.state.errorMessage = errorMessage;
    }

  });
});