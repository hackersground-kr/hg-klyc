import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./page/LogIn";
import Signup from "./page/SignUp";
import PostList from "./page/PostList";
import CreatePost from "./page/CreatePost";

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
