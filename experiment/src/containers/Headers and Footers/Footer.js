import React from "react";

const FinalFooter = () => {




return(

<div className="ui primary menu" style={{position: 'fixed', left: 0, right: 0, bottom: 0, backgroundColor: 'black', zIndex: 999}}>
  <a className="item" href="https://my.cerner.com" style={{textAlign: 'center', alignItems: 'center', width: 'auto', margin: 'auto'}}>
   <i style={{color: 'white'}}> &#169; Copyright 2022 Cerner Corporation. All rights reserved. </i>
  </a>
  </div>

  );
};

export default FinalFooter;