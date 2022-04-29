import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure ,shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import "core-js/stable";
import "regenerator-runtime/runtime";
import AllIdeaHeader from "../containers/Headers and Footers/AllIdeasHeader";


configure({ adapter: new Adapter() });


it("check for test cases",()=> {
    const renderer = new ShallowRenderer();
    renderer.render(<AllIdeaHeader />);
    expect(renderer).toMatchSnapshot();

})
