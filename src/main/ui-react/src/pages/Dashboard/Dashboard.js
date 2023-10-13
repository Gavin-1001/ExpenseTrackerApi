import './Dashboard.css'

const Dashboard = () => {

    return (
        <div className="container">

            <div className="title-searchcontainer">
                <div className="title">
                    Expense Tracker
                </div>
                <div className="search">
                    <input type={"text"} placeholder={"input"} />
                </div>
            </div>


            <div className="sevenDayPurchases">

            </div>

            <div className="sevenDayPurchases">
                <h6>Purchases this month</h6>
                <p>6</p>
            </div>

            {/*Both of these need to be fixed*/}

            <div className="sevenDayPurchases">
                Purchases today
                10 purchases today
            </div>
            <div className="sevenDayPurchaseBigContainer">
                <div className="sevenDayPurchasesBig">
                    maps
                </div>
            </div>

        </div>
    )
}

export default Dashboard;
