import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure ,render,shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import "core-js/stable";
import "regenerator-runtime/runtime";
import { createTestStore } from './testStore.test';
import { Provider } from "react-redux";
import { UpdateIdea } from "../containers/UpdateForm";
import AllIdeaDetails from "../containers/AllIdeaDetails";
import App from '../App';

configure({ adapter: new Adapter() });

let store;
describe("Your test1", () => {

    beforeEach(() => {
        store = createTestStore();
    });

    it("check for test cases",()=> {
        const renderer = new ShallowRenderer();
        renderer.render(
            <Provider store={store}>
            <AllIdeaDetails />
            </Provider>
            );
        expect(renderer).toMatchSnapshot();
    })

    it("check for test cases2",()=> {
        const renderer = new ShallowRenderer();
        renderer.render(
            <Provider store={store}>
            <UpdateIdea />
            </Provider>
            );
        expect(renderer).toMatchSnapshot();
    })

    it("check for test cases app",()=> {
        const renderer = new ShallowRenderer();
        renderer.render(
            <Provider store={store}>
            <App />
            </Provider>
            );
        expect(renderer).toMatchSnapshot();
    })

})