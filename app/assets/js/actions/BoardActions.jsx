define(function (require, exports, module) {
  var alt = require('../alt');
  var $ = require('jquery');
  return alt.createActions({

    updateBoard: function(board) {
      this.dispatch(board);
    },

    fetchBoard: function(id) {
      this.dispatch();
      $.get('/instance/'+id+'/board', function(data) {
        this.actions.updateBoard(data);
      }.bind(this));
    },

    fetchFailed: function(errorMessage) {
      this.dispatch(errorMessage);
    }

  });
});