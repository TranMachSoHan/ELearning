import React, { useEffect, useState } from "react";
import { Doughnut, Pie, Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  BarElement,
} from "chart.js";
import {
  countStudentMajor,
  getProfessorsString,
  getStudents,
} from "../API/APIultils";

export const Stats = () => {
  ChartJS.register(
    ArcElement,
    Tooltip,
    Legend,
    CategoryScale,
    LinearScale,
    BarElement
  );
  const [studentCount, setStudentCount] = useState(4900);
  const [professorCount, setProfessorCount] = useState(100);
  const [IT, setIT] = useState(10);
  const [BM, setBM] = useState(20);
  const [DM, setDM] = useState(15);
  const [SE, setSE] = useState(25);
  useEffect(() => {
    const fetchData = async () => {
      try {
        let student = await getStudents();
        setStudentCount(Object.keys(student).length);
        let professor = await getProfessorsString();
        setProfessorCount(Object.keys(professor).length);
        let major = await countStudentMajor();
        setIT(major.countIT);
        setBM(major.countBM);
        setSE(major.countSE);
        setDM(major.countDM);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);
  return (
    <div class=" mt-5 grid w-full lg:grid-cols-2 md:grid-cols-2 p-4 gap-3">
      <div class="flex flex-col p-8 bg-white rounded shadow-sm">
        <b class="flex flex-row text-gray-500">
          Number of Student in each Major
        </b>
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
                label: "Student",
                backgroundColor: ["#B1BAFB", "#6475F7", "#3142C4", "#182162"],
                data: [IT, BM, SE, DM],
              },
            ],
          }}
        />
      </div>

      <div class=" flex flex-col p-5 bg-white rounded shadow-sm">
        <b class="flex flex-row text-gray-500">Student / Professor Segments</b>
        <Pie
          data={{
            labels: ["Teacher", "Student"],
            datasets: [
              {
                label: "Users",
                backgroundColor: ["#B1BAFB", "#3D53F5"],
                data: [professorCount, studentCount],
              },
            ],
          }}
        />
      </div>
      <div class="col-span-2 flex flex-col p-8 bg-white rounded shadow-sm">
        <b class="flex flex-row text-gray-500">Number of Account created</b>
        <Bar
          data={{
            labels: ["July", "August", "Septemer", "November", "December"],
            datasets: [
              {
                label: "Account",
                backgroundColor: [
                  "#182162",
                  "#182162",
                  "#182162",
                  "#182162",
                  "#182162",
                ],
                data: [2478, 5267, 734, 784, 433],
              },
            ],
          }}
        />
      </div>
    </div>
  );
};
