import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../style/Login.css'; // CSS 파일 경로

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
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

  return (
    <div className="login-container">
      <h2 className="login-title">게시팜</h2>
      <form onSubmit={handleLogin} className="login-form">
        <div className="input-container">
          <label htmlFor="username" className="sr-only">아이디 또는 전화번호</label>
          <input
            type="text"
            id="username"
            placeholder="아이디 또는 전화번호"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="input-container">
          <label htmlFor="password" className="sr-only">비밀번호</label>
          <input
            type="password"
            id="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit" className="login-button">로그인</button>
      </form>
      <div className="signup-link">
        아직 계정이 없다면? <a href="/signup">회원가입으로</a>
      </div>
    </div>
  );
}

export default Login;
