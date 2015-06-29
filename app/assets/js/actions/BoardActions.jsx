define(function (require, exports, module) {
  var alt = require('../alt');
  var $ = require('jquery');
  return alt.createActions({

    updateBoard: function(board) {
      this.dispatch(board);
    },

    fetchBoard: function() {
      this.dispatch();
      $.get('/instance/371/board', function(data) {
        this.actions.updateBoard(data);
      }.bind(this));
    },

    fetchFailed: function(errorMessage) {
      this.dispatch(errorMessage);
    }

  });
});