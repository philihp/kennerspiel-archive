import React, { Component } from 'react'

import Board from './Board.jsx'
import BoardStore from '../stores/BoardStore.jsx'

class App extends Component {
    componentDidMount() {
        BoardStore.fetchBoard()
    }

    render() {
        return (
            <div>
                <Board />
            </div>
        )
    }
}

export default App