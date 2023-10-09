import './Dashboard.css'

const Dashboard = () => {

    return(

        <div className="container">

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

            <div className="sevenDayPurchasesBig">
                maps
            </div>

        </div>
    )
}

export default Dashboard;
