/* eslint-disable jsx-a11y/anchor-is-valid */
import React from "react";
import DropdownForViewAll from "./dropDownForViewAll";

const AllIdeaHeader = () => {



return(

<div className="ui pointing menu" style={{position: 'fixed', left: 0, right: 0, top: 0, backgroundColor: 'black', zIndex: 999}}>

<a className="active item" style={{color: 'white'}}>
  <b>You are viewing other ideas available in the Repository</b>
  </a>
  
  <div className="right menu">
  <DropdownForViewAll />
  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>


  </div>
  </div>

  );
};

export default AllIdeaHeader;