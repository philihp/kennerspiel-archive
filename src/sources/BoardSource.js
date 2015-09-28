import axios from 'axios';
import { api } from '../utils/api'

import BoardActions from '../actions/BoardActions'

export default {
    fetchBoard: {
        remote() {
            return axios.post(api(), 'CONFIG(PLAYERS,3)|CONFIG(LENGTH,LONG)|CONFIG(COUNTRY,FRANCE)').then(res => {
                res.data
            })
        },
        success: BoardActions.boardFetched,
        error: BoardActions.boardFailed,
    }
}