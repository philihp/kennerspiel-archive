import alt from '../alt'
import makeHot from 'alt/utils/makeHot'
import BoardActions from '../actions/BoardActions'
import BoardSource from '../sources/BoardSource'

class BoardStore {
    static displayName = 'BoardStore';

    constructor() {
        this.state = {
            errorMessage: null,
            board: {},
        }
        this.bindActions(BoardActions)
        this.bindListeners({
            //moveAdded: BoardActions.moveAdded,
        })
        this.registerAsync(BoardSource)
        this.on('beforeEach', () => {
            this.setState({errorMessage: null})
        })
    }

    boardFetched(board) {
        this.setState({board});
    }

    boardFailed(errorMessage) {
        this.setState({errorMessage});
    }

    moveAdded([board, move]) {
    }

}

export default makeHot(alt, BoardStore)