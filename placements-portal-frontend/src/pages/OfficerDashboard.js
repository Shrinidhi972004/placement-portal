import React, { useEffect, useState } from "react";
import axios from "axios";
import "./OfficerDashboard.css";

function OfficerDashboard({ user }) {
  const [drives, setDrives] = useState([]);
  const [applicants, setApplicants] = useState([]);
  const [loading, setLoading] = useState(true);

  // Fetch drives and applicants on mount
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        // Fetch drives created by officer (or all, depending on backend API)
        const drivesRes = await axios.get("http://localhost:8080/api/drives");
        setDrives(drivesRes.data);

        // Fetch applicants - update this endpoint as per your backend
        const applicantsRes = await axios.get("http://localhost:8080/api/applicants");
        setApplicants(applicantsRes.data);
      } catch (error) {
        // Handle error or show message
        setDrives([]);
        setApplicants([]);
      }
      setLoading(false);
    };

    fetchData();
  }, []);

  // Placeholder handlers for future edit, delete, add, approve actions
  const handleEditDrive = (driveId) => {
    alert(`Edit drive ${driveId} (to be implemented)`);
  };
  const handleDeleteDrive = (driveId) => {
    alert(`Delete drive ${driveId} (to be implemented)`);
  };
  const handleAddDrive = () => {
    alert("Add new drive (to be implemented)");
  };
  const handleApproveApplicant = (applicantId) => {
    alert(`Approve applicant ${applicantId} (to be implemented)`);
  };

  return (
    <div className="officer-dashboard-container">
      <h2 className="officer-dashboard-title">Placement Officer Dashboard</h2>
      <div className="officer-dashboard-section">
        <h3>Manage Company Drives</h3>
        {loading ? (
          <p>Loading drives...</p>
        ) : drives.length === 0 ? (
          <p>No drives found.</p>
        ) : (
          <ul>
            {drives.map((drive) => (
              <li key={drive.id}>
                {drive.companyName} - {drive.role}
                <button className="officer-edit-btn" onClick={() => handleEditDrive(drive.id)}>Edit</button>
                <button className="officer-delete-btn" onClick={() => handleDeleteDrive(drive.id)}>Delete</button>
              </li>
            ))}
          </ul>
        )}
        <button className="officer-add-btn" onClick={handleAddDrive}>+ Add New Drive</button>
      </div>
      <div className="officer-dashboard-section">
        <h3>Applicants</h3>
        {loading ? (
          <p>Loading applicants...</p>
        ) : applicants.length === 0 ? (
          <p>No applicants found.</p>
        ) : (
          <ul>
            {applicants.map((applicant) => (
              <li key={applicant.id}>
                {applicant.name} ({applicant.companyName}) -{" "}
                <button className="officer-approve-btn" onClick={() => handleApproveApplicant(applicant.id)}>
                  Approve
                </button>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default OfficerDashboard;
