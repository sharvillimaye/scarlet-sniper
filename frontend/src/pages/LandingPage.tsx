import {Link} from "react-router-dom";

function LandingPage() {
    return (
        <div>
            <h1>Landing Page</h1>
            <p>Scarlet Sniper is a comprehensive course sniping tool.</p>
            <Link to="/login">Login</Link><br/>
            <Link to="/signup">Register</Link><br/>
        </div>
    );
}

export default LandingPage;
