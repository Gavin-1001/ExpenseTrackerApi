import './Dashboard.css'
import Sidebar from "../../components/Navigation/Sidebar/Sidebar";
import {useEffect, useState} from "react";
import ExpenseService from "../../services/ExpenseService.service/ExpenseService";
import PieChart from "../../components/metrics/charts/PieChart/PieChart";
import LineChart from "../../components/metrics/charts/LineChart/LineChart";

const Dashboard = () => {

    const [count, setCount] = useState(null);
    const [countMonth, setCountMonth] = useState(null);
    const [errorMessage, setErrorMessage] = useState("")



    useEffect(() => {
        // Fetch data from your Spring Boot endpoint
        ExpenseService.getWeeklyExpenses()
            .then((response) => {
                setCount(response.data);
            })
            .catch((error) => {
                console.error('Error fetching expenses:', error);
            });

        ExpenseService.getMonthlyExpenses()
            .then((response) => {
                setCountMonth(response.data);
            })
            .catch((error) => {
                console.error('Error fetching monthly expenses:', error);
                setErrorMessage("Error fetching monthly expenses")
            });
    }, []);


    return (

        <div className="container">
            <Sidebar/>
            <div className="windowsContainer" style={{marginTop: "7rem"}}>
                <div className="row row-cols-2 row-cols-lg-3">

                    <div className="col-5 col-xl-2 windows" style={{backgroundColor: "mediumpurple"}}>
                        <h5>Purchases last week</h5>
                        {count !== null ? (
                            <p className={""}> {count}</p>
                        ) : (
                            <p>Loading...</p>
                        )}
                        <div className="invalid-feedback">Purchase error</div>
                    </div>
                    <div className="col-5 col-xl-2 windows" style={{backgroundColor: "lightseagreen"}}>
                        <h5>Purchases last month</h5>
                        {countMonth !== null ? (
                            <p className={""}> {countMonth}</p>
                        ) : (
                            <p>Loading...</p>
                        )}
                    </div>
                    <div className="col-4 col-xl-2 windows">
                        Have top expenses here for the week and the month
                    </div>

                    <div className="col-4 col-xl-5 largeWindow" style={{backgroundColor: "paleturquoise"}}>
                        <PieChart />

                    </div>
                </div>
                <div className="row row-cols-2 row-cols-lg-3">
                    <div className="col-5 col-xl-9" style={{backgroundColor: "mediumpurple", marginTop: "8.5rem", marginBottom: "8.9rem" }}>
                        <p>Large graph goes here</p>
                        <LineChart />
                    </div>
                </div>
            </div>

        </div>
    )

}

export default Dashboard;
