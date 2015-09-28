import React, { Component } from 'react'
import connectToStores from 'alt/utils/connectToStores'
import BoardStore from '../stores/BoardStore'

class Board extends Component {
    render() {
        return (
            <div>Board</div>
        )
    }
}

export default connectToStores({
    getStores() {
        return [BoardStore]
    },
    getPropsFromStores(props) {
        console.log('Board.connectToStores.getPropsFromStores(',BoardStore.getState(),')');
        const state = BoardStore.getState()
        return {
            board: BoardStore.getState().board
        }
    }
}, Board)