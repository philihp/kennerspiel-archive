define(function (require, exports, module) {
  var alt = require('../alt');

  class BoardActions {
    updateBoard(board) {
      this.dispatch(board);
    }
  }

  module.exports = alt.createActions(LocationActions);
});