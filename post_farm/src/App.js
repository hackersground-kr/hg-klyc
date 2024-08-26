import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "../src/page/Login/LogIn";
import Signup from "../src/page/SignUp/SignUp";
import PostList from "../src/page/PostList/PostList";
import CreatePost from "../src/page/CreatePost/CreatePost";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/posts" element={<PostList />} />
        <Route path="/posts/create" element={<CreatePost />} />
      </Routes>
    </Router>
  );
}

export default App;
