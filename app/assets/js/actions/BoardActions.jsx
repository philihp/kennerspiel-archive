define(function (require, exports, module) {
  var alt = require('../alt');
  var $ = require('jquery');

  return alt.createActions({

    updateBoard: function(board) {
      this.dispatch(board);
    },

    fetchBoard: function(token) {
      this.dispatch();
    },

    fetchFailed: function(errorMessage) {
      this.dispatch(errorMessage);
    },

    addCommand: function(moveString) {
      this.dispatch(moveString);
      this.actions.fetchBoard();
    },

    undoCommand: function() {
      this.dispatch();
      this.actions.fetchBoard();
    },

    commitMove: function() {
      this.dispatch();
    }

  });
});