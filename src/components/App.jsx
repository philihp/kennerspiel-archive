import React, { Component } from 'react'
import { Col, Grid, Row } from 'react-bootstrap'

import Board from './Board'
import BoardStore from '../stores/BoardStore'

class App extends Component {
    componentDidMount() {
        BoardStore.fetchBoard()
    }

    render() {
        return (
            <div>
                <Grid>
                    <Row>
                        <Col sm={2} smPull={1}>
                            <Board />
                        </Col>
                    </Row>
                </Grid>
            </div>
        )
    }
}

export default App