import { useEffect, useState } from "react";
import { NavLink, useNavigate, useLocation } from "react-router-dom";
import { getAllSkills } from "../api/useCourseAPI";
import { ACCESS_TOKEN } from "../constants";
import { useAuth } from "../context/AuthContext";
import Button from "./Button";
import ProfileLink from "./ProfileLink";


const TopNav = () => {
  const [open, setOpen] = useState(false);
  const location = useLocation();
  const navigate = useNavigate();

  const [skillList, setSkillList] = useState(["Python", "ReactJs", "C", "Java", "NodeJs"]);


  //const [userName, setUserName] = useState(null);

  const [subMenuText, setSubMenuText] = useState("Skills");

  useEffect(() => {
    let pathArgs = location.pathname.split("/");
    if (pathArgs[1] === "skill") {
      setSubMenuText(pathArgs[2]);
    } else {
      setSubMenuText("Skills");
    }
  }, [location.pathname]);

  useEffect(() => {
    const getSkills = async () => {
      let data = await getAllSkills();

      setSkillList(data)
    }

    getSkills()
  }, [])

  //Check if student logged in
  const { user } = useAuth();

  return (
    <nav className="flex items-center justify-between pt-14">
      {/* Logo */}
      <h4
        className="font-black text-black cursor-pointer text-headline-21 lg:text-headline-31"
        onClick={() => {
          navigate("/");
        }}
      >
        Rademy
      </h4>

      {/* Navigation List Begins */}
      <ul className="hidden lg:flex items-center justify-between  xl:max-w-[83.33%] flex-grow">
        <li
          className="flex capitalize gap-1.5 items-center relative cursor-pointer "
          onMouseEnter={() => {
            setOpen(true);
          }}
          onMouseLeave={() => {
            setOpen(false);
          }}
        >
          <span>{subMenuText.toLowerCase()}</span>{" "}
          <svg
            width="25"
            height="24"
            viewBox="0 0 25 24"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fillRule="evenodd"
              clipRule="evenodd"
              d="M5.54289 8.29289C5.90338 7.93241 6.47061 7.90468 6.8629 8.2097L6.95711 8.29289L12.25 13.585L17.5429 8.29289C17.9034 7.93241 18.4706 7.90468 18.8629 8.2097L18.9571 8.29289C19.3176 8.65338 19.3453 9.22061 19.0403 9.6129L18.9571 9.70711L12.9571 15.7071C12.5966 16.0676 12.0294 16.0953 11.6371 15.7903L11.5429 15.7071L5.54289 9.70711C5.15237 9.31658 5.15237 8.68342 5.54289 8.29289Z"
              fill="#171717"
            />
          </svg>
          <ul
            className={
              "absolute z-10 right-0 -bottom-0 transform translate-y-full py-4 space-y-2 bg-white drop-shadow-lg " +
              (open ? "block" : "hidden")
            }
          >
            {skillList.map((skill) => (
              <li>
                <NavLink
                  to={`skill/${skill}`}
                  className="block px-6 py-1 hover:bg-primary-500 hover:text-white"
                >
                  {skill}
                </NavLink>
              </li>
            ))}
          </ul>
        </li>

        <li>
          <input
            type="text"
            name="searchBar"
            id="searchBar"
            className="px-4 py-2 w-[524px] rounded-full bg-grey-300"
            placeholder="Search for a course..."
          />
        </li>

        <li>
          <NavLink to={"/"} className="flex gap-1.5 items-center">
            <svg
              width="25"
              height="24"
              viewBox="0 0 25 24"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M11.5429 2.29289C11.9034 1.93241 12.4707 1.90468 12.863 2.2097L12.9572 2.29289L21.9572 11.2929C22.5597 11.8955 22.1777 12.9072 21.3637 12.994L21.2501 13H20.2501V19C20.2501 20.5977 19.0011 21.9037 17.4263 21.9949L17.2501 22H15.2501H9.25005H7.25005C5.65237 22 4.34639 20.7511 4.25514 19.1763L4.25005 19V13H3.25005C2.39788 13 1.95262 12.0145 2.46683 11.3775L2.54294 11.2929L11.5429 2.29289ZM10.2501 20H14.2501V15C14.2501 14.4872 13.864 14.0645 13.3667 14.0067L13.2501 14H11.2501C10.7372 14 10.3145 14.386 10.2568 14.8834L10.2501 15V20ZM16.2501 20V15L16.245 14.8237C16.1537 13.2489 14.8477 12 13.2501 12H11.2501L11.0738 12.0051C9.49897 12.0963 8.25005 13.4023 8.25005 15V20H7.25005L7.13343 19.9933C6.63609 19.9355 6.25005 19.5128 6.25005 19V12L6.24332 11.8834C6.20333 11.5391 5.98845 11.2481 5.68983 11.1016C5.66091 11.0875 5.63121 11.0746 5.60081 11.0632L12.2501 4.415L18.8986 11.0635C18.8684 11.0748 18.839 11.0876 18.8103 11.1016L18.8103 11.1016C18.4785 11.2644 18.2501 11.6055 18.2501 12V19L18.2433 19.1166C18.1856 19.614 17.7629 20 17.2501 20H16.2501Z"
                fill="#171717"
              />
            </svg>
            <span>Home</span>
          </NavLink>
        </li>

        {/* Display login/sing up buttons or authenticated user */}
        {!user?.email ? (
          <li className="flex items-center gap-1.5">
            <Button
              text={"Log In"}
              onClick={() => {
                navigate("/login");
              }}
              isPrimary={false}
              size="small"
            ></Button>
            <Button
              text={"Sign Up"}
              onClick={() => {
                navigate("/register");
              }}
              isPrimary={true}
              size="small"
            ></Button>
          </li>
        ) : (
          <li>
            <ProfileLink role={"student"} name={user?.name} userID={user?.id} />
          </li>
        )}
      </ul>
      {/* Navigation List Ends */}
    </nav>
  );
};

export default TopNav;
