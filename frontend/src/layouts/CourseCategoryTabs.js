import { useEffect, useState } from "react";
import Button from "../components/Button";
import SectionTitle from "../components/SectionTitle";
import TabContent from "../components/TabContent";
import TabItem from "../components/TabItem";
import { getCourseBySkillGroups } from "../api/useCourseAPI";
import { useNavigate } from "react-router-dom";

const CourseCategoryTabs = () => {
  const [courseData, setCourseData] = useState([]);
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
    if(courseData.length > 0) {
      setActiveSkillName(courseData.find((c) => c.isActive === true).skill);
    } 
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
                    instructor={professor?.name}
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
