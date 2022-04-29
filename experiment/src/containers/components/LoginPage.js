import React from 'react';
import { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import axios from 'axios';
import LoginHeader from '../Headers and Footers/LoginHeader';
import FinalFooter from '../Headers and Footers/Footer';
import { Popup } from 'semantic-ui-react';

export const LoginPage = () => {
    
    const [errorMessage, setErrorMessage] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] =useState('');
    const [isValid, setIsValid] = useState(false);
    const [message, setMessage] = useState('');

    const history = useHistory();

    const regEx1 = /[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,8}(.[a-z{2,8}])?/g;
    
    function checkLogin() {
        //checking localstorage for current user
        if (typeof window !== 'undefined') {
        if(localStorage.getItem('email')){
            history.push('/idealisting')
        }
    }
    }
    checkLogin();

    const usernamelValidation = (e) => {
       
        setUsername(e.target.value);
        if (regEx1.test(username)) {
            setIsValid(true);
          setMessage("");
        } else {
            setIsValid(false);
          setMessage("Enter a valid username.");
        }
      };

    const onLoginClicked = async () => {
        await axios.post('http://localhost:8090/login', {
        username: username,
        password: password
      })
      .then(res => {
        //storing the token and username in the localstorage
        if(res.data.token){
          localStorage.setItem('token', res.data.token)
          localStorage.setItem('email', username)
        }
        history.push('/idealisting')
            })
    .catch(function (error) {
        setErrorMessage("Please check your entries and try again!", error);
        if (typeof window !== 'undefined') {

            localStorage.clear();
            }
    });
    }

    return(
        <div className="content-container">
        <LoginHeader />
            <h1 style={{color:"lightblue", fontSize: "25px", textAlign: "center", fontStyle: "-moz-initial", fontVariant: "all-petite-caps"}}>Log-in to your account</h1>
            {errorMessage && <div className="fail" style={{color: "lightgreen"}}>{errorMessage}</div>}
            <input
                id='input-username'
                pattern='/[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,8}(.[a-z{2,8}])?/g'
                value={username}
                style={{backgroundColor: 'transparent', border: '1.5px solid white', lineHeight: '20px', color: 'white'}}
                onChange={usernamelValidation}
                className='user icon'
                placeholder="username" />
                <div className={`message ${isValid ? 'success' : 'error'}`} style={{color: 'lightgreen'}}>
                    {message}
                </div>
            <input
                id='input-password'
                type="password"
                style={{backgroundColor: 'transparent', border: '1.5px solid white', lineHeight: '20px', color: 'white'}}
                value={password}
                onChange={e =>setPassword(e.target.value)}
                placeholder="password" />
                <br />

                <button id='btn-primary' className="ui animated button" style={{backgroundColor: 'transparent', border: '3px solid white', color: 'white'}} tableindex="0" 
                    disabled={!username || !password || !isValid}
                    onClick={()=>onLoginClicked()}>
                    <div className="visible content" style={{color: 'lightgrey'}}>Log In</div>
                    <div className="hidden content">
                    <i className="sign in alternate icon" style={{color: 'lightgreen'}}></i></div>
                </button>
                    <hr />
                    <h4 className="mt-2" style={{color: 'lightgrey'}}>
                    &nbsp;&nbsp;&nbsp;&nbsp;Don't have an account? <Link to="/registration" style={{color: 'yellow'}}>&nbsp;Register Here</Link>
                </h4>
        <FinalFooter />
        </div>
    )
}

