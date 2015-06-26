define(function (require, exports, module) {
  var alt = require('../alt');

  var BoardActions = alt.createActions({
    updateBoard: function(board) {
      this.dispatch(board);
    }
  });

  module.exports = BoardActions;
});