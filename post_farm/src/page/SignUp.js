import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../style/Signup.css"; // CSS 파일 경로
import delIcon from "../images/del.png"; // 삭제 버튼 이미지 경로
import eyeOpenIcon from "../images/eye_1.png"; // 비밀번호 표시 아이콘 이미지 경로
import eyeClosedIcon from "../images/eye_2.png";

function Signup() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      // 환경 변수로 설정된 API URL을 사용하여 요청을 보냅니다.
      await axios.post(`${process.env.REACT_APP_API_URL}/api/signup`, {
        username,
        password,
      });
      navigate("/login");
    } catch (error) {
      console.error("Signup failed", error);
      alert("Error signing up");
    }
  };

  const clearUsername = () => {
    setUsername("");
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div className="login-container">
      <h2 className="login-title">게시팜</h2>
      <form onSubmit={handleSignup} className="login-form">
        <div className="input-container">
          <input
            type="text"
            id="username"
            placeholder="아이디 또는 전화번호"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <button
            type="button"
            onClick={clearUsername}
            className="delete-button"
          >
            <img src={delIcon} alt="Delete" />
          </button>
        </div>
        <div className="input-container">
          <input
            type={showPassword ? "text" : "password"}
            id="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button
            type="button"
            onClick={togglePasswordVisibility}
            className="toggle-password"
          >
            <img
              src={showPassword ? eyeOpenIcon : eyeClosedIcon}
              alt="Toggle Password"
            />
          </button>
        </div>
        <button type="submit" className="login-button">
          회원가입
        </button>
      </form>
      <div className="signup-link">
        이미 계정이 있으시다면? <a href="/login">로그인</a>으로
      </div>
    </div>
  );
}

export default Signup;
