import 'babel-core/polyfill';
import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import App from './containers/App';
// import DevTools from './containers/DevTools';

import configureStore from './store/configureStore';

const store = configureStore();

const rootElement = document.getElementById('root');

render(
  // TODO: DevTools shouldn't be included on Production, similar to configureStore
  <Provider store={store}>
    <div>
      <App />
    </div>
  </Provider>,
  rootElement
);

// <DevTools />
