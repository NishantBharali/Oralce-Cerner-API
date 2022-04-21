import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure ,render } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import "core-js/stable";
import "regenerator-runtime/runtime";
import { createTestStore } from "./testStore.test";
import { Provider } from "react-redux";
import { UpdateIdea } from "../containers/UpdateForm";
import AllIdeaDetails from "../containers/AllIdeaDetails";
import AllIdeaListing from "../containers/AllIdeaListing";
import AllIdeaComponents from "../containers/AllIdeaComponents";

configure({ adapter: new Adapter() });

let store;
describe("Your test", () => {

    beforeEach(() => {
        store = createTestStore();
    });

test('Components with a full reducer flow 1', async () => {
    const renderer = new ShallowRenderer();
    renderer.render(
        <Provider store={store}>
        <AllIdeaDetails />
        </Provider>
    );

});

test('Components with a full reducer flow update', async () => {
    const renderer = new ShallowRenderer();
    renderer.render(
        <Provider store={store}>
        <UpdateIdea />
        </Provider>
    );

});

test('Components with a full reducer flow all ideas', async () => {
    const { list } = render(
        <Provider store={store}>
        <AllIdeaListing />
        </Provider>
    );

});

test('Components with a full reducer flow all idea comps', async () => {
    const {temp } = render(
        <Provider store={store}>
        <AllIdeaComponents />
        </Provider>
    );

});


});