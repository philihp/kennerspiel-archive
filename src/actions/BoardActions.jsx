var alt = require('../alt');
import axios from 'axios';
import { api } from '../utils/api'

export default alt.createActions({
    displayName: 'BoardActions',
    boardFetched(board) {
        console.log('BoardActions.boardFetched(',board,')')
        this.dispatch(board)
    },
    boardFailed(error) {
        this.dispatch(err)
    }
})