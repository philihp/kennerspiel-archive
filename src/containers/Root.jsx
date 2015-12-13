import React, { Component } from 'react';
import { Provider } from 'react-redux';
// React components for Redux DevTools
// import { DevTools, DebugPanel, LogMonitor } from 'redux-devtools/lib/react';

import configureStore from '../configureStore';
import App from './App';

const store = configureStore();

export default class Root extends Component {
  render() {
    // TODO: Remove DebugPanel on Production
    return (
      <div>
        <Provider store={store}>
          {() => <App />}
        </Provider>
      </div>
    );
    // <DebugPanel top right bottom>
    //   <DevTools store={store} monitor={LogMonitor} />
    // </DebugPanel>
  }
}
