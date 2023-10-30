import './PieChart.css'
import {Pie} from "react-chartjs-2";
import {Chart, ArcElement} from 'chart.js'
Chart.register(ArcElement);
const PieChart = () => {

    const data = {
        labels: ['Red', 'Blue', 'Yellow', 'Green'],
        datasets: [
            {
                data: [12, 19, 3, 5],
                backgroundColor: ['red', 'blue', 'yellow', 'green'],

            },
        ],
    };

    return (
        <div className="container">
            <div className={"pieStyle"}>
                <Pie data={data}/>
            </div>
        </div>
    )
}

export default PieChart;
