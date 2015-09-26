import React, { Component } from 'react'
import BoardStore from '../stores/BoardStore'

class Board extends Component {
    componentDidMount() {
        BoardStore.fetchBoard()
    }
    render() {
        return (
            <div>Board</div>
        )
    }
}

export default Board