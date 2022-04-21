import { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import validator from 'validator';
import axios from 'axios';
import LoginHeader from '../Headers and Footers/LoginHeader';
import FinalFooter from '../Headers and Footers/Footer';

export const RegistrationPage = () => {
    const [errorMessage, setErrorMessage] = useState('');
    
    const [email, setEmail] = useState('');
    const [password, setPassword] =useState('');
    const [confirmPassword, setConfirmPassword] =useState('');
    const [isValid, setIsValid] = useState(false);
    const [isValid1, setIsValid1] = useState(false);
    const [isValid2, setIsValid2] = useState(false);
    const [message, setMessage] = useState('');
    const [message1, setMessage1] = useState('');
    const [message2, setMessage2] = useState('');
    const history = useHistory();

    const regEx = /[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,8}(.[a-z{2,8}])?/g;

    const emailValidation = (e) => {
        e.preventDefault();
        setEmail(e.target.value);
        if (regEx.test(email)) {
            setIsValid(true);
          setMessage("Email is valid.");
        } else {
            setIsValid(false);
          setMessage("Please enter a valid email.");
        }
      };

      const passwordValidation = (e) => {
          e.preventDefault();
          setPassword(e.target.value);
          if(validator.isStrongPassword(password, {
              minLength: 8, minLowercase: 1,
              minUppercase: 1, minNumbers: 1, minSymbols: 1
          })) {
              setIsValid1(true);
              setMessage1('This is a strong password.')
          } else {
              setIsValid1(false);
              setMessage1('Minimum 8 characters, an Upper case leter, a numeral and a symbol.')
          }
      };

      const confirmPasswordValidation = (e) => {
        setConfirmPassword(e.target.value);
        if(validator.isStrongPassword(confirmPassword, {
            minLength: 8, minLowercase: 1,
            minUppercase: 1, minNumbers: 1, minSymbols: 1
        })) {
            setIsValid2(true);
            setMessage2('')
        } else {
            setIsValid2(false);
            setMessage2('')
        }
    };

    const onRegisterClicked = async () => {
        await axios.post('http://localhost:8090/user', {
            email: email,
            password: password
      })
      .then(resp => {
        history.push('/');
      })
      .catch(function (error) {
        setErrorMessage("User is already registered!", error);
    });
      
    }

    return(

        <div className="content-container1">
        <LoginHeader />
            <h1 style={{color:"lightblue", fontSize: "25px", textAlign: "center", fontStyle: "-moz-initial", fontVariant: "all-petite-caps"}}>Create an account</h1>
                {errorMessage && <div className="fail" style={{color: "lightgreen"}}>{errorMessage}</div>}
            <input
                id="userName-input"
                value={email}
                style={{backgroundColor: 'transparent', border: '1.7px solid white', lineHeight: '20px', color: 'white'}}
                onChange={emailValidation}
                placeholder="enter your email" />

                <div className={`message ${isValid ? 'success' : 'error'}`} style={{color: 'lightgreen'}}>
                    {message}
                </div>

            <input
                type="password"
                value={password}
                style={{backgroundColor: 'transparent', border: '1.7px solid white', lineHeight: '20px', color: 'white'}}
                onChange={passwordValidation}
                placeholder="enter your password" />

                <div className={`message ${isValid1 ? 'success' : 'error'}`} style={{color: 'lightgreen'}}>
                    {message1}
                </div>

            <input
                type="password"
                value={confirmPassword}
                style={{backgroundColor: 'transparent', border: '1.7px solid white', lineHeight: '20px', color: 'white'}}
                onChange={confirmPasswordValidation}
                placeholder="confirm your password" />

                <div className={`message ${isValid2 ? 'success' : 'error'}`} style={{color: 'lightgreen'}}>
                    {message2}
                </div>

                <hr />
                <button id="btn-register" className="ui animated button" style={{backgroundColor: 'transparent', border: '3px solid white', color: 'white'}} tableindex="0"
                    disabled={!password || !isValid || !isValid1 || !isValid2 || password !== confirmPassword}
                    onClick={()=>onRegisterClicked()}>
                    <div className="visible content" style={{color: 'lightgray'}}>Register</div>
                    <div className="hidden content">
                        <i className="chevron right icon" style={{color: 'lightblue'}}></i>
                    </div>
                </button>

                <hr />
                <h4 className="mt-2" style={{color: 'lightgrey'}}>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Already have an account? <Link to="/" style={{color: 'lightgreen'}}>&nbsp;Login Here</Link>
            </h4>
        <FinalFooter />
        </div>
    )
}
