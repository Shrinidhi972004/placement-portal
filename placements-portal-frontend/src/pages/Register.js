import React, { useState } from "react";
import axios from "axios";
import "./Register.css";

function Register() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    role: "STUDENT", // Default role
    cgpa: "",
    skills: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    try {
      // Convert cgpa to float if present
      const payload = {
        ...form,
        cgpa: form.role === "STUDENT" && form.cgpa ? parseFloat(form.cgpa) : undefined,
        skills: form.role === "STUDENT" ? form.skills : undefined,
      };
      await axios.post("http://localhost:8080/api/auth/register", payload);
      setSuccess("Registration successful! You can now log in.");
      setForm({
        name: "",
        email: "",
        password: "",
        role: "STUDENT",
        cgpa: "",
        skills: "",
      });
    } catch (err) {
      setError("Registration failed! " + (err.response?.data?.message || ""));
    }
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      <form className="register-form" onSubmit={handleRegister}>
        <label>Name</label>
        <input name="name" value={form.name} onChange={handleChange} required />

        <label>Email</label>
        <input name="email" type="email" value={form.email} onChange={handleChange} required />

        <label>Password</label>
        <input name="password" type="password" value={form.password} onChange={handleChange} required />

        <label>Role</label>
        <select name="role" value={form.role} onChange={handleChange}>
          <option value="STUDENT">Student</option>
          <option value="OFFICER">Placement Officer</option>
        </select>

        {form.role === "STUDENT" && (
          <>
            <label>CGPA</label>
            <input
              name="cgpa"
              type="number"
              step="0.01"
              value={form.cgpa}
              onChange={handleChange}
              required
            />
            <label>Skills</label>
            <input name="skills" value={form.skills} onChange={handleChange} required />
          </>
        )}

        <button type="submit">Register</button>
        {error && <div className="register-error">{error}</div>}
        {success && <div className="register-success">{success}</div>}
      </form>
    </div>
  );
}

export default Register;

