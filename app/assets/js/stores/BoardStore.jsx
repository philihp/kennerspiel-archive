define(function (require, exports, module) {
  var alt = require('../alt');
  var BoardActions = require('../actions/BoardActions');
  return alt.createStore({
    displayName: 'BoardStore',

    bindListeners: {
      handleUpdateBoard: BoardActions.updateBoard,
      handleFetchBoard: BoardActions.fetchBoard,
      handleFetchFailed: BoardActions.fetchFailed,
      handleAddCommand: BoardActions.addCommand,
      handleUndoCommand: BoardActions.undoCommand,
      handleCommitMove: BoardActions.commitMove
    },

    state: {
      board: null,
      token: "",
      errorMessage: null
    },

    publicMethods: {
    },

    handleUpdateBoard: function(board) {
      this.state.board = board;
      this.state.errorMessage = null;
    },

    handleFetchBoard: function() {
      $.get('/instance/'+Window.INSTANCE_ID+'/board?move='+this.state.token, function(data) {
        BoardActions.updateBoard(data);
      }.bind(this));

      // this is where you'd put a loading thing if you wanted it to flicker
    },

    handleFetchFailed: function(errorMessage) {
      this.state.errorMessage = errorMessage;
    },

    handleAddCommand: function(command) {
      this.state.token = command;
    },

    handleUndoCommand: function() {
      var commands = this.state.token.split('|');
      commands.splice(-1, 1);
      this.state.token = commands.join('|');
    },

    handleCommitMove: function() {
    }

  });
});
