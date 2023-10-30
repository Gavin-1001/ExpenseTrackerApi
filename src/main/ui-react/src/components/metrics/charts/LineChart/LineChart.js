import {Chart, ArcElement} from 'chart.js'
import {Line} from "react-chartjs-2";

const LineChart = () => {

    const data = {
        labels: ['January', 'February', 'March', 'April', 'May'],
        datasets: [
            {
                label: 'Monthly Sales',
                data: [50, 45, 70, 60, 80],
                borderColor: 'blue',
                fill: false,
            },
        ],
    };

    const options = {
        scales: {
            y: {
                beginAtZero: true,
            },
        },
    };

    return (
        <div>
            <h2>Line Chart Example</h2>
            <div style={{ width: '400px', height: '400px' }}>
                <Line data={data} options={options} />
            </div>
        </div>
    )
}

export default LineChart;
