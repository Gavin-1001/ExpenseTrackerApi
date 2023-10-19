import './Dashboard.css'
import Sidebar from "../../components/Navigation/Sidebar/Sidebar";


const Dashboard = () => {




    return (

        <div className="container">
            <Sidebar />
            <div className="windowsContainer" style={{marginTop: "7rem"}}>
                <div className="row row-cols-2 row-cols-lg-3">

                    <div className="col-5 col-xl-2 windows" style={{backgroundColor: "mediumpurple"}}>
                        <h5>Purchases last week</h5>
                        <p>number of purchases{}</p>
                    </div>
                    <div className="col-5 col-xl-2 windows" style={{backgroundColor: "lightseagreen"}}>
                        <h5>Purchases last month</h5>
                        <p>number of purchases{}</p>
                        {/* interpolate it */}
                    </div>
                    <div className="col-4 col-xl-2 windows">
                        Something else
                    </div>

                    <div className="col-4 col-xl-5 largeWindow" style={{backgroundColor: "paleturquoise"}}>Graph or something</div>
                </div>
                <div className="row row-cols-2 row-cols-lg-3">
                    <div className="col-5 col-xl-9" style={{backgroundColor: "mediumpurple", marginTop: "8.5rem"}}>
                        <p>Large graph goes here</p>
                    </div>
                </div>
            </div>

        </div>
    )

}

export default Dashboard;
