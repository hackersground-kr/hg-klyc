import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../style/SignUp.css'; // CSS 파일 경로
import delIcon from '../images/del.png'; // 삭제 버튼 이미지 경로
import eyeOpenIcon from '../images/eye_1.png'; // 비밀번호 표시 아이콘 이미지 경로
import eyeClosedIcon from '../images/eye_2.png'; // 비밀번호 숨기기 아이콘 이미지 경로

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/api/login', { username, password });
      localStorage.setItem('token', response.data.token);
      navigate('/posts');
    } catch (error) {
      console.error('Login failed', error);
      alert('Invalid credentials');
    }
  };

  const clearUsername = () => {
    setUsername('');
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div className="login-container">
      <h2 className="login-title">게시팜</h2>
      <form onSubmit={handleLogin} className="login-form">
        <div className="input-container">
          <input
            type="text"
            id="username"
            placeholder="아이디 또는 전화번호"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <button type="button" onClick={clearUsername} className="delete-button">
            <img src={delIcon} alt="Delete" />
          </button>
        </div>
        <div className="input-container">
          <input
            type={showPassword ? 'text' : 'password'}
            id="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button type="button" onClick={togglePasswordVisibility} className="toggle-password">
            <img src={showPassword ? eyeOpenIcon : eyeClosedIcon} alt="Toggle Password" />
          </button>
        </div>
        <button type="submit" className="login-button">회원가입</button>
      </form>
      <div className="signup-link">
        이미 계정이 있다면? <a href="/Login">로그인</a>으로
      </div>
    </div>
  );
}

export default Login;
