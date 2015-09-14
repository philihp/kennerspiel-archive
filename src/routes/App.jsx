//import * as less from './App.less';
import React from 'react';
import { RouteHandler, Link } from 'react-router';
import Header from '../components/Header'

// A route handler that contains the entirety of the application.
export default class App extends React.Component {
  render() {
    return (
      <div className='container'>
        <Header />
        <RouteHandler />
      </div>
    );
  }
}