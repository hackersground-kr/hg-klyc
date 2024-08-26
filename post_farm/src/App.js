import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Main from "../src/page/Main";
import Login from "../src/page/LogIn";
import Signup from "../src/page/SignUp";
import PostList from "../src/page/PostList";
import CreatePost from "../src/page/CreatePost";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/Main" element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/posts" element={<PostList />} />
        <Route path="/posts/create" element={<CreatePost />} />
      </Routes>
    </Router>
  );
}

export default App;
