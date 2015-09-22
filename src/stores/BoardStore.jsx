var alt = require('../alt');
var BoardActions = require('../actions/BoardActions');
//var BoardSource = require('../sources/BoardSource');

class BoardStore {

  constructor() {
    this.displayName = 'BoardStore';
    this.board = null;
    this.token = "";
    this.errorMessage = null;

    this.bindListeners({
      handleUpdateBoard: BoardActions.updateBoard,
      handleFetchBoard:  BoardActions.fetchBoard,
      handleFetchFailed: BoardActions.fetchFailed,
      handleAddCommand:  BoardActions.addCommand,
      handleUndoCommand: BoardActions.undoCommand,
      handleCommitMove:  BoardActions.commitMove
    });
    this.exportPublicMethods({
      getBoard: this.getBoard
    });

    //this.exportAsync(BoardSource);
  }

  handleUpdateBoard(board) {
    this.state.board = board;
    this.state.errorMessage = null;
  }

  handleFetchBoard() {
    $.get('/instance/'+Window.INSTANCE_ID+'/board?move='+this.state.token, function(data) {
      BoardActions.updateBoard(data);
    }.bind(this));

    // this is where you'd put a loading thing if you wanted it to flicker
  }

  handleFetchFailed(errorMessage) {
    this.state.errorMessage = errorMessage;
  }

  handleAddCommand(command) {
    this.state.token = command;
  }

  handleUndoCommand() {
    var commands = this.state.token.split('|');
    commands.splice(-1, 1);
    this.state.token = commands.join('|');
  }

  handleCommitMove() {
  }
}

module.exports = BoardStore