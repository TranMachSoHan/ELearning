import React, { useEffect, useState } from 'react'
import { Doughnut, Pie, Bar } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend, CategoryScale, LinearScale, BarElement } from "chart.js";
import { getProfessorsString, getStudents } from '../API/APIultils';

export const Stats = () => {
    ChartJS.register(ArcElement, Tooltip, Legend, CategoryScale, LinearScale, BarElement)
    const [studentCount, setStudentCount] = useState(0)
    const [professorCount, setProfessorCount] = useState(0)
    useEffect(() => {
        const fetchData = async () => {
            try {
                let student = await getStudents()
                await setStudentCount(Object.keys(student).length)
                console.log(await student.filter(value => value.major === "IT").length);
                let professor = await getProfessorsString()
                await setProfessorCount(Object.keys(professor).length)
            } catch (error) {
                console.log(error)
            }
        }
        fetchData();
    }, [])
    return (
        <div class=" mt-5 grid w-full lg:grid-cols-2 md:grid-cols-2 p-4 gap-3">
            <div class="flex flex-col p-8 bg-white rounded shadow-sm">
                <b class="flex flex-row text-gray-500">Property Release for today</b>
                <Doughnut
                    data={{
                        labels: [
                            "IT",
                            "Business Management",
                            "Software Engineering",
                            "Digital Marketing",
                        ],
                        datasets: [
                            {
                                label: "Population (millions)",
                                backgroundColor: [
                                    "#B1BAFB",
                                    "#6475F7",
                                    "#3142C4",
                                    "#182162"
                                ],
                                data: [2478, 5267, 734, 784]
                            }
                        ]
                    }}
                />
            </div>

            <div class=" flex flex-col p-5 bg-white rounded shadow-sm">
                <b class="flex flex-row text-gray-500">Student / Professor Segments</b>
                <Pie
                    data={{
                        labels: [
                            "Teacher",
                            "Student"
                        ],
                        datasets: [
                            {
                                label: "Users",
                                backgroundColor: [
                                    "#B1BAFB",
                                    "#3D53F5"
                                ],
                                data: [professorCount, studentCount]
                            }
                        ]
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
                />
            </div>

        </div>
    )
}
