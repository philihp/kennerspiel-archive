var alt = require('../alt');

class BoardActions {
  updateBoard(board) {
    this.dispatch(board);
  }

  fetchBoard(token) {
    this.dispatch();
  }

  fetchFailed(errormessage) {
    this.dispatch(errorMessage);
  }

  addCommand(moveString) {
    this.dispatch(moveString);
    this.actions.fetchboard();
  }

  undoCommand() {
    this.dispatch();
    this.actions.fetchBoard();
  }

  commitMove() {
    this.dispatch();
  }
}
