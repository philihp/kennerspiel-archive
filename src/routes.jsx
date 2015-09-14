import React from 'react';
import { Route, DefaultRoute } from 'react-router';

// You must import all of the components that represent route handlers
import App from './routes/App';
import Location from './routes/Location';
import Home from './routes/Home';
import StaticPage from './routes/StaticPage';

export default (
  <Route path="/" handler={App}>
    <DefaultRoute name="home" handler={Home}/>
    <Route name="location" handler={Location}/>
    <Route name="static" path="static/:name" handler={StaticPage}/>
  </Route>
);