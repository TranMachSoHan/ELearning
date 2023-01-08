import React from 'react'
import { Doughnut, Pie, Bar } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend, CategoryScale, LinearScale, BarElement } from "chart.js";

export const Stats = () => {
    ChartJS.register(ArcElement, Tooltip, Legend, CategoryScale, LinearScale, BarElement);
    return (
        <div class=" mt-5 grid w-full lg:grid-cols-2 md:grid-cols-2 p-4 gap-3">
            <div class="flex flex-col p-8 bg-white rounded shadow-sm">
                <b class="flex flex-row text-gray-500">Property Release for today</b>
                <Doughnut
                    data={{
                        labels: [
                            "Africa",
                            "Asia",
                            "Europe",
                            "Latin America",
                            "North America"
                        ],
                        datasets: [
                            {
                                label: "Population (millions)",
                                backgroundColor: [
                                    "#3e95cd",
                                    "#8e5ea2",
                                    "#3cba9f",
                                    "#e8c3b9",
                                    "#c45850"
                                ],
                                data: [2478, 5267, 734, 784, 433]
                            }
                        ]
                    }}
                    option={{
                        title: {
                            display: true,
                            text: "Predicted world population (millions) in 2050"
                        }
                    }}
                />
            </div>

            <div class=" flex flex-col p-5 bg-white rounded shadow-sm">
                <b class="flex flex-row text-gray-500">Occupancy Percentage</b>
                <Pie
                    data={{
                        labels: [
                            "Africa",
                            "Asia",
                            "Europe",
                            "Latin America",
                            "North America"
                        ],
                        datasets: [
                            {
                                label: "Population (millions)",
                                backgroundColor: [
                                    "#3e95cd",
                                    "#8e5ea2",
                                    "#3cba9f",
                                    "#e8c3b9",
                                    "#c45850"
                                ],
                                data: [2478, 5267, 734, 784, 433]
                            }
                        ]
                    }}
                    option={{
                        title: {
                            display: true,
                            text: "Predicted world population (millions) in 2050"
                        }
                    }}
                />
            </div>
            <div class="col-span-2 flex flex-col p-8 bg-white rounded shadow-sm">
                <b class="flex flex-row text-gray-500">Property Release for today</b>
                <Bar
                    data={{
                        labels: [
                            "Africa",
                            "Asia",
                            "Europe",
                            "Latin America",
                            "North America"
                        ],
                        datasets: [
                            {
                                label: "Population (millions)",
                                backgroundColor: [
                                    "#3e95cd",
                                    "#8e5ea2",
                                    "#3cba9f",
                                    "#e8c3b9",
                                    "#c45850"
                                ],
                                data: [2478, 5267, 734, 784, 433]
                            }
                        ]
                    }}
                    options={{
                        legend: { display: false },
                        title: {
                            display: true,
                            text: "Predicted world population (millions) in 2050"
                        }
                    }}
                />
            </div>

        </div>
    )
}
