import { compose, createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import { persistState } from 'redux-devtools';
import createLogger from 'redux-logger';
import DevTools from '../containers/DevTools';
import rootReducer from '../reducers';

const loggerMiddleware = createLogger();

function getDebugSessionKey() {
  // You can write custom logic here!
  // By default we try to read the key from ?debug_session=<key> in the address bar
  const matches = window.location.href.match(/[?&]debug_session=([^&]+)\b/);
  return (matches && matches.length > 0) ? matches[1] : null;
}

const composeStore = compose(
  applyMiddleware(thunkMiddleware, loggerMiddleware),
  DevTools.instrument(),
  persistState(getDebugSessionKey())
)(createStore);

export default function configureStore(initialState) {
  const store = composeStore(rootReducer, initialState);

  // Hot reload reducers (requires Webpack or Browserify HMR to be enabled)
  if (module.hot) {
    module.hot.accept('../reducers', () =>
      store.replaceReducer(require('../reducers').default)
    );
  }

  return store;
}
