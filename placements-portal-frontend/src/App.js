import { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";

// Page imports
import Landing from "./pages/Landing";
import Login from "./pages/Login";
import Register from "./pages/Register";
import StudentDashboard from "./pages/StudentDashboard";
import OfficerDashboard from "./pages/OfficerDashboard";
import Drives from "./pages/Drives";
import Notifications from "./pages/Notifications";
import EditProfile from "./pages/EditProfile";
import DriveForm from "./pages/DriveForm";
import NotFound from "./pages/NotFound";

function App() {
  // User state for login persistence
  const [user, setUser] = useState(null);

  // On app load, get user from localStorage
  useEffect(() => {
    const savedUser = localStorage.getItem("user");
    if (savedUser) setUser(JSON.parse(savedUser));
  }, []);

  return (
    <Router>
      <Navbar user={user} setUser={setUser} />
      <Routes>
        {/* Public pages */}
        <Route path="/" element={<Landing />} />
        <Route path="/login" element={<Login setUser={setUser} />} />
        <Route path="/register" element={<Register />} />

        {/* Student routes (protected) */}
        <Route
          path="/student/dashboard"
          element={
            user && user.role === "STUDENT" ? (
              <StudentDashboard user={user} />
            ) : (
              <Navigate to="/login" />
            )
          }
        />
        <Route
          path="/student/drives"
          element={
            user && user.role === "STUDENT" ? (
              <Drives user={user} />
            ) : (
              <Navigate to="/login" />
            )
          }
        />
        <Route
          path="/edit-profile"
          element={
            user ? <EditProfile user={user} /> : <Navigate to="/login" />
          }
        />

        {/* Officer routes (protected) */}
        <Route
          path="/officer/dashboard"
          element={
            user && user.role === "OFFICER" ? (
              <OfficerDashboard user={user} />
            ) : (
              <Navigate to="/login" />
            )
          }
        />
        <Route
          path="/officer/drive-form"
          element={
            user && user.role === "OFFICER" ? (
              <DriveForm user={user} />
            ) : (
              <Navigate to="/login" />
            )
          }
        />

        {/* Common */}
        <Route
          path="/notifications"
          element={user ? <Notifications user={user} /> : <Navigate to="/login" />}
        />

        {/* Catch-all */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
