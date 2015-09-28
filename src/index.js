import React from 'react'
import { Router, Route, Link } from 'react-router'
import { history } from 'react-router/lib/HashHistory'
import App from './components/App'

React.render((
        <Router history={history}>
            <Route path="/" component={App}/>
        </Router>
    ),
    document.body
)