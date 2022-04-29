import { ideaReducer, initialState, selectedIdeaReducer } from '../redux/reducers/ideaReducer';
import expect from 'expect';
import { runSaga } from 'redux-saga';
import { ActionTypes } from "../redux/constants/action-types";
import "core-js/stable";
import { createTestStore } from "./testStore.test";
import { addIdeas, deleteIdea, getAllIdeas, selectedIdeas, setIdeas, updateIdea } from '../redux/actions/ideaActions';

test('selector gives back the idea', () => {
    const ideas1 = [{ideaTitle: 'test', ideaDescription: 'test', ideaStorypoints: 1}];
    const res = setIdeas(ideas1);
    expect(res.payload).toBe(ideas1)
})

test('selector gives back all the ideas', () => {
    const ideas1 = [{ideaTitle: 'test', ideaDescription: 'test', ideaStorypoints: 1},
{ideaTitle:'test1', ideaDescription: 'test1', ideaStorypoints: 2}];
    const res = getAllIdeas(ideas1);
    expect(res.payload).toBe(ideas1)
})

test('selector gives back the selected idea', () => {
    const ideas1 = [{ideaTitle: 'test2', ideaDescription: 'test2', ideaStorypoints: 3}];
    const res = selectedIdeas(ideas1);
    expect(res.payload).toBe(ideas1)
})

test('selector gives back the added idea', () => {
    const ideas1 = [{ideaTitle: 'test3', ideaDescription: 'test3', ideaStorypoints: 4}];
    const res = addIdeas(ideas1);
    expect(res.payload).toBe(ideas1)
})

test('selector gives back the updated idea', () => {
    const ideas1 = [{ideaTitle: 'test4', ideaDescription: 'test4', ideaStorypoints: 5}];
    const ideas2 = [{ideaTitle: 'test4', ideaDescription: 'test4', ideaStorypoints: 5}];
    const res = updateIdea(ideas1);
    expect(res.payload).toStrictEqual(ideas2)
})

test('selector delete the given idea', () => {
    const ideas1 = [{ideaTitle: 'test6', ideaDescription: 'test6', ideaStorypoints: 6}];
    const res = deleteIdea(ideas1);
    expect(res.payload).toBe(ideas1)
})
