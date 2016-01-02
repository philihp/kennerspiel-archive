import { compose, createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import createLogger from 'redux-logger';
import rootReducer from '../reducers';

const loggerMiddleware = createLogger();

const composeStore = compose(
  applyMiddleware(thunkMiddleware, loggerMiddleware)
)(createStore);

export default function configureStore(initialState) {
  return composeStore(rootReducer, initialState);
}
