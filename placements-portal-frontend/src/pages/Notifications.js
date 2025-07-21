import "./Notifications.css";

function Notifications() {
  return (
    <div className="notifications-container">
      <h2 className="notifications-title">Notifications</h2>
      <ul className="notifications-list">
        {/* Replace with dynamic notifications */}
        <li className="notification-card new">
          <strong>New Drive Posted:</strong> Company A hiring for SDE.
        </li>
        <li className="notification-card">
          <strong>Application Update:</strong> Your application for Company B has been shortlisted!
        </li>
        <li className="notification-card">
          <strong>Interview Scheduled:</strong> Company A interview on 3rd Aug, 10 AM.
        </li>
      </ul>
    </div>
  );
}

export default Notifications;

