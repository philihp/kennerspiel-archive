import React, { Component } from 'react'

import Board from './Board'
import BoardStore from '../stores/BoardStore'

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