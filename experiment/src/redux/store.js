import { applyMiddleware, createStore, compose } from "redux";
import reducers from './reducers/index';
import createSagaMiddleware from "@redux-saga/core";
import { watcherSaga } from "./sagas/watcherSaga";

const sagaMiddleware = createSagaMiddleware();

const middlewareEnhancer = applyMiddleware(sagaMiddleware)

const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store = createStore(
    reducers, 
    {},
    composeEnhancer(middlewareEnhancer),
    
    );

    sagaMiddleware.run(watcherSaga);



export default store;
