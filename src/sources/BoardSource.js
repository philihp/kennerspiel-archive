import axios from 'axios';
import { api } from '../utils/api'

import BoardActions from '../actions/BoardActions'

export default {
    fetchBoard: {

        remote(state) {
            return axios.post(api(), 'CONFIG(PLAYERS,3)|CONFIG(LENGTH,LONG)|CONFIG(COUNTRY,FRANCE)')
                        .then(response => response.data)
        },

        success: BoardActions.boardFetched,

        error: BoardActions.boardFailed,

        shouldFetch(state) {
            return true
        }
    }
}