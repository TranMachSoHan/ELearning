import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getTeacherProfileById } from "../api/useAuthAPI";
import Avatar from "../assets/instructor-ava.jpg";
import SectionTitle from "../components/SectionTitle";

const TeacherDetail = () => {
  const { instructorID } = useParams();
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    const getProfile = async () => {
      let res = await getTeacherProfileById(instructorID);
      // console.log(std)
      setProfile(res);
    };
    getProfile();
  }, []);

  return (
    <section className="pt-10">
      <div className="bg-primary-50 h-[600px] grid mb-10">
        <div className="flex items-center self-center gap-7 container-padding-left">
          <img
            src={profile?.avatar}
            className="block aspect-square w-[148px] rounded-full"
            alt=""
          />
          <div>
            <h1 className="font-bold text-primary-500 text-headline-48">
              {profile?.name}
            </h1>
          </div>
        </div>
      </div>

      <div>
        <SectionTitle title={"About Me"}></SectionTitle>
        <p>{profile?.description}</p>
      </div>
    </section>
  );
};

export default TeacherDetail;
