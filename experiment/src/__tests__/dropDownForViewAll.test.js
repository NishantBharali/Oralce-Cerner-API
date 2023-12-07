import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import "core-js/stable";
import "regenerator-runtime/runtime";
import DropdownForViewAll from "../containers/Headers and Footers/dropDownForViewAll";

configure({ adapter: new Adapter() });


it("check for test cases",()=> {
    const renderer = new ShallowRenderer();
    renderer.render(<DropdownForViewAll />);
    expect(renderer).toMatchSnapshot();

})
