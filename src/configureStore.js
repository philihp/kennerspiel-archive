import { compose, createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import createLogger from 'redux-logger';
import rootReducer from './reducers';
// Redux DevTools store enhancers
import { devTools, persistState } from 'redux-devtools';

const loggerMiddleware = createLogger();

const createStoreWithMiddleware = compose(
  applyMiddleware(thunkMiddleware,loggerMiddleware),
  // TODO Remove when in production
  devTools()
)(createStore);

export default function configureStore(initialState) {
  return createStoreWithMiddleware(rootReducer, initialState);
}
