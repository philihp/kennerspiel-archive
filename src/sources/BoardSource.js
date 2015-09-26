import axios from 'axios';
import { api } from '../utils/api'

import BoardActions from '../actions/BoardActions'

export default {
    fetchBoard: {
        remote() {
            return axios.get(api()).then(res => res.data)
        },
        success: BoardActions.boardFetched,
        error: BoardActions.boardFailed,
    }
}