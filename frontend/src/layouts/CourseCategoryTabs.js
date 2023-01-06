import { useEffect, useState } from "react";
import Button from "../components/Button";
import SectionTitle from "../components/SectionTitle";
import TabContent from "../components/TabContent";
import TabItem from "../components/TabItem";
import { getCourseBySkillGroups } from "../api/useCourseAPI";
import { useNavigate } from "react-router-dom";

const data = [
  {
    skill: "Python",
    isActive: true,
    listCourses: [
      {
        courseName: "Python for Absolute Beginners",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "Python for Intermediate Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "Python for Senior Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
    ],
  },
  {
    skill: "ReactJs",
    isActive: false,
    listCourses: [
      {
        courseName: "Excel for Absolute Beginners",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "Excel for Intermediate Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "Excel for Senior Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
    ],
  },
  {
    skill: "C",
    isActive: false,
    listCourses: [
      {
        courseName: "C++ for Absolute Beginners",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "C++ for Intermediate Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "C++ for Senior Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
    ],
  },
  {
    skill: "NodeJs",
    isActive: false,
    listCourses: [
      {
        courseName: "Android Development for Absolute Beginners",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "Android Development for Intermediate Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "Android Development for Senior Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
    ],
  },
  {
    skill: "Java",
    isActive: false,
    listCourses: [
      {
        courseName: "Google Analytics for Absolute Beginners",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "Google Analytics for Intermediate Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
      {
        courseName: "Google Analytics for Senior Students",
        courseDes:
          "Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.",
        instructor: "Hoang Minh Quan",
      },
    ],
  },
];

const CourseCategoryTabs = () => {
  const [courseData, setCourseData] = useState(data);
  const [activeSkillName, setActiveSkillName] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      let data = await getCourseBySkillGroups();

      let courseToShow = [];
      for (const [index, [key, value]] of Object.entries(data).entries()) {
        console.log(key);
        courseToShow.push({
          skill: key,
          isActive: index === 0 ? true : false,
          listCourses: value,
        });
      }

      setCourseData(courseToShow);
    };
    fetchData();
  }, []);

  useEffect(() => {
    setActiveSkillName(courseData.find((c) => c.isActive === true).skill);
  }, [courseData]);

  const tabClick = (tabName) => {
    setCourseData((preCourseData) => {
      return preCourseData.map((course) => {
        if (course.skill === tabName) {
          return { ...course, isActive: true };
        } else {
          return { ...course, isActive: false };
        }
      });
    });
  };

  const toCoursePath = () => {
    navigate(`skill/${activeSkillName}`);
  };

  console.log(courseData);

  return (
    <section className="">
      <SectionTitle title={"A broad selection of courses"}></SectionTitle>

      <div className="px-12 py-10 mt-3 border border-grey-900">
        <div className="flex items-center gap-8 mb-14">
          {courseData?.map(({ skill, isActive }) => (
            <TabItem
              content={skill}
              onClick={tabClick}
              isActive={isActive}
            ></TabItem>
          ))}
        </div>

        <div className="space-y-7">
          {courseData?.map(({ isActive, listCourses }) => {
            if (isActive) {
              return listCourses?.map(
                ({ courseName, courseDescription, courseID, professor }) => (
                  <TabContent
                    courseID={courseID}
                    courseName={courseName}
                    courseDes={courseDescription}
                    instructor={professor?.professorName}
                    key={courseID}
                  />
                )
              );
            }
          })}
          <div className="flex justify-center">
            <Button
              isPrimary={true}
              size="large"
              className={"capitalize"}
              onClick={toCoursePath}
              text={`See More ${activeSkillName?.toLowerCase()} Courses`}
            ></Button>
          </div>
        </div>
      </div>
    </section>
  );
};

export default CourseCategoryTabs;
