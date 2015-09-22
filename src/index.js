import React from 'react'
import { Router, Route, Link } from 'react-router'
import { history } from 'react-router/lib/HashHistory'
import Board from './components/Board'

React.render((
    <Router history={history}>
        <Route path="/" component={Board}/>
    </Router>
), document.getElementById('root'));