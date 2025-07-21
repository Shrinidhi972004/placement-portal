import "./Drives.css";

function Drives() {
  return (
    <div className="drives-container">
      <h2 className="drives-title">Available Company Drives</h2>
      <ul className="drives-list">
        {/* Replace with dynamic data later */}
        <li className="drive-card">
          <div>
            <h3>Company A</h3>
            <p>Role: Software Engineer</p>
            <p>Eligibility: CGPA 7.0+</p>
            <p>Last Date: 30th July</p>
          </div>
          <button className="apply-btn">Apply</button>
        </li>
        <li className="drive-card">
          <div>
            <h3>Company B</h3>
            <p>Role: Analyst</p>
            <p>Eligibility: CGPA 8.0+</p>
            <p>Last Date: 2nd August</p>
          </div>
          <button className="apply-btn">Apply</button>
        </li>
      </ul>
    </div>
  );
}

export default Drives;

