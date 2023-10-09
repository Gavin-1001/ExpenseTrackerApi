import './Sidebar.css'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faGear} from "@fortawesome/free-solid-svg-icons/faGear";
import {faChartLine, faHouse} from "@fortawesome/free-solid-svg-icons";
import {NavLink} from "react-router-dom";

const Sidebar = () => {

    return (
        <div className={"container"}>
            <div className="sidebar-container">
                <nav id="sidebar" className="custom-sidebar">
                    <div className="sidebar-header">
                    </div>
                    <ul className="list-unstyled components">
                        <li>
                            <NavLink to={'/home'}>
                                <FontAwesomeIcon icon={faHouse}/>
                            </NavLink>
                        </li>
                        <li>
                            <NavLink to={'/dashboard'}>
                                <FontAwesomeIcon icon={faChartLine} style={{color: "#ffffff",}} />
                            </NavLink>
                        </li>
                        <li>
                            <a href="#about">About</a>
                        </li>
                        {/* Move the "Services" link to the bottom */}
                        <li className="fixed-bottom">
                            <NavLink to="/settings">
                                <FontAwesomeIcon icon={faGear} size="lg" style={{color: "#f7f7f4",}}/>
                            </NavLink>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    )


}

export default Sidebar;
