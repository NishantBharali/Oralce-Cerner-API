/* eslint-disable jsx-a11y/anchor-is-valid */
import React from "react";

const ComponentHeader = () => {



return(

<div className="ui pointing menu" style={{position: 'fixed', left: 0, right: 0, top: 0, backgroundColor: 'black', zIndex: 999}}>

<a className="active item" style={{color: 'white'}}>
<i className="user circle icon" style={{marginTop: '1px'}}></i>
  &nbsp;&nbsp;{typeof window !== 'undefined'? localStorage.getItem('email') :null}
  </a>
  
  <div className="right menu">


  </div>
  </div>

  );
};

export default ComponentHeader;