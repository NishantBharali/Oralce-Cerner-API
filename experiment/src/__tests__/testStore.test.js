import { combineReducers, createStore } from "redux";
import { ideaReducer, selectedIdeaReducer } from "../redux/reducers/ideaReducer";


export function createTestStore() {
    const store = createStore(
        combineReducers({
            allIdeas: ideaReducer,
            idea: selectedIdeaReducer
        })

    );
    return store;
}