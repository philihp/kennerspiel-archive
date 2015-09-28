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
        })
        this.registerAsync(BoardSource)
        this.on('beforeEach', () => {
            this.setState({errorMessage: null})
        })
    }

    boardFetched(board) {
        this.setState({board});
    }

    boardFailed(error) {
        console.error(error.data.message);
        this.setState({error});
    }

    moveAdded([board, move]) {
    }

}

export default makeHot(alt, BoardStore)