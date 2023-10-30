import Chart from 'chart.js/auto';
import {Line} from "react-chartjs-2";
import ExpenseService from "../../../../services/ExpenseService.service/ExpenseService";
import {useEffect, useState} from "react";
import axios from "axios";
import {BASE_API_URL} from "../../../../api/baseUrl";

const LineChart = () => {
    const [chartData, setChartData] = useState({});



    useEffect(() => {
        // Define the URL of your Spring Boot API endpoint
        const apiUrl = BASE_API_URL +'/api/expense/getCategoryCount';

        // Fetch data from the API using Axios
        axios.get(apiUrl)
            .then((response) => {
                // Assuming your API returns an array of objects with data
                const data = response.data;

                // Extract data for the chart
                const categoryNames = data.map((item) => item.categoryName);
                const categoryCounts = data.map((item) => item.categoryCount);
                //see chatgpt for error with item.category

                // Define the chart data
                const chartData = {
                    labels: categoryNames,
                    datasets: [
                        {
                            label: 'Category Counts',
                            data: categoryCounts,
                            borderColor: 'rgba(75, 192, 192, 1)', // Line color
                            borderWidth: 2, // Line width
                            fill: false, // Do not fill under the line
                        },
                    ],
                };

                // Update the state with the chart data
                setChartData(chartData);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
            });
    }, []);

    return (
        <div>
            <h2>Line Chart Example</h2>
            <div style={{ width: '400px', height: '400px' }}>
                <Line data={chartData} />
            </div>
        </div>
    );
}

export default LineChart;
