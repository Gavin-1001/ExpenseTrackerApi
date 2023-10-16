import './Sidebar.css'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faGear} from "@fortawesome/free-solid-svg-icons/faGear";
import {faChartLine, faHouse, faRightFromBracket} from "@fortawesome/free-solid-svg-icons";
import { NavLink, useNavigate} from "react-router-dom";
import {faCalculator} from "@fortawesome/free-solid-svg-icons/faCalculator";
import {useDispatch, useSelector} from "react-redux";
import {clearCurrentUser} from "../../../redux/store/actions/users";


const Sidebar = () => {

    const navigate = useNavigate();
    const dispatch = useDispatch();
    const currentUser = useSelector((state) => state.user);

    const logout = () => {
        dispatch(clearCurrentUser());
        navigate("/signin");
    };

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
                            <NavLink to={'/expenseForm'}>
                                <FontAwesomeIcon icon={faCalculator} style={{color: "#ffffff"}} />
                            </NavLink>
                        </li>

                        <li>
                            <a href="#about">About</a>
                        </li>
                        {/* Move the "Services" link to the bottom */}
                        <li>
                            <NavLink to="/settings">
                                <FontAwesomeIcon icon={faGear} size="lg" style={{color: "#f7f7f4"}}/>
                            </NavLink>
                        </li>
                        <li>
                            <NavLink to="/logout" >
                                <FontAwesomeIcon icon={faRightFromBracket} style={{color: "#f7f7f4"}} onClick={() => logout()}/>
                            </NavLink>
                        </li>



                    </ul>
                </nav>
            </div>
        </div>
    )


}

export default Sidebar;
