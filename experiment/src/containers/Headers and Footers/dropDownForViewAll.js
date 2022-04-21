import React from 'react';
import { useHistory } from 'react-router-dom';
import { Button, ButtonGroup, Dropdown } from 'semantic-ui-react';


const DropdownForViewAll = () => {

  const history = useHistory();

const onLogoutClicked = () => {
  if(window.confirm("are you sure you want to logout?")){
  if (typeof window !== 'undefined') {
  localStorage.clear();
  }
  history.replace('/');
}
}


const options = [
  { key: 'Home', text: (<Button className="ui animated button" style={{backgroundColor: 'transparent'}} onClick={() => history.push('/idealisting')}><div className="visible content">Home</div>
  <div className="hidden content">
      <i className="home icon"></i></div></Button>), value: 'Home' },
  { key: 'Logout', text: (<Button className="ui animated button" style={{backgroundColor: 'transparent'}} onClick={()=>onLogoutClicked()}><div className="visible content">Logout</div>
  <div className="hidden content">
      <i className="power off icon"></i></div></Button>), value: 'Logout' },
]



  return(
  <ButtonGroup>
    <Button style={{backgroundColor: 'transparent', color: 'floralwhite'}}>
    <i className="user circle icon" style={{color: 'lightgreen'}}></i>{typeof window !== 'undefined'? localStorage.getItem('email') :null}</Button>
    <Dropdown
      className='button icon' style={{padding: '10px', height: '90%', marginTop: '2px', backgroundColor: 'transparent', color: 'white'}}
      floating
      options={options}
      trigger={<></>}
    />
  </ButtonGroup>
  
  )

  
      };

export default DropdownForViewAll;