var alt = require('../alt');
import axios from 'axios';
import { api } from '../utils/api'

export default alt.createActions({
    displayName: 'BoardActions',
    boardFetched: board => board,
    boardFailed: err => err,
})