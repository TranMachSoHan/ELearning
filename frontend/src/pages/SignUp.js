import { useState, useEffect } from "react";
import Button from "../components/Button";
import SectionTitle from "../components/SectionTitle";
import { signup } from "../Utils/APIUltils";
import { useNavigate } from "react-router-dom";
import { uploadAvatar } from "../api/useCourseAPI";

const SignUp = () => {
  const navigate = useNavigate();
  const [registerType, setRegisterType] = useState("null");

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [major, setMajor] = useState("");
  const [minor, setMinor] = useState("");
  const [bank, setBank] = useState("");
  const [accountNumber, setAccountNumber] = useState("");
  const [avatar, setAvatar] = useState();
  const [instructorDescription, setInstructorDescription] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("file", avatar);
    let avpath = await uploadAvatar(formData);

    let signUpRequest;
    if (registerType === "student") {
      signUpRequest = Object.assign(
        {},
        {
          email: email,
          password: password,
          name: name,
          major: major,
          minor: minor,
          userRole: "STUDENT",
          avatar: `https://elearning-sead-storage.s3.ap-south-1.amazonaws.com/
          ${avpath.filePath}`,
          payment: {
            bank: bank,
            accountNumber: accountNumber,
            name: name,
            dateIssued: new Date(),
          },
        }
      );
    } else if (registerType === "instructor") {
      signUpRequest = Object.assign(
        {},
        {
          email: email,
          password: password,
          name: name,
          userRole: "PROFESSOR",
          avatar: `https://elearning-sead-storage.s3.ap-south-1.amazonaws.com/
          ${avpath.filePath}`,
          description: instructorDescription,
        }
      );
    }
    signup(signUpRequest, registerType).then((response) => {
      console.log(response);
      console.log(registerType);
      navigate("/login");
    });
  };

  const handlePreviewAvatar = (e) => {
    const file = e.target.files[0];
    file.preview = URL.createObjectURL(file);
    setAvatar(file);
  };

  useEffect(() => {
    return () => {
      avatar && URL.revokeObjectURL(avatar.preview);
    };
  }, [avatar]);

  return (
    <section className="min-h-[700px] pt-24">
      <SectionTitle title="Sign Up"></SectionTitle>

      <form onSubmit={handleSubmit} className="w-1/4 pt-20 mx-auto space-y-6">
        <div className="space-y-2 ">
          <label htmlFor="avatar">Avatar</label>
          <input
            type="file"
            name="avatar"
            id="avatar"
            className="block w-full p-2 border border-black"
            placeholder="John Doe"
            onChange={handlePreviewAvatar}
          />
          {avatar && (
            <img
              src={avatar.preview}
              alt=""
              className="w-36 mx-auto h-36 rounded-full border border-grey-100 shadow-sm"
            />
          )}
        </div>
        <div className="space-y-2">
          <label htmlFor="name">Name</label>
          <input
            type="text"
            name="name"
            id="name"
            className="block w-full p-2 border border-black"
            placeholder="John Doe"
            onChange={(e) => setName(e.target.value)}
            value={name}
          />
        </div>

        <div className="space-y-2">
          <label htmlFor="email">Email</label>
          <input
            type="text"
            name="email"
            id="email"
            className="block w-full p-2 border border-black"
            placeholder="email@gmail.com"
            onChange={(e) => setEmail(e.target.value)}
            value={email}
          />
        </div>
        <div className="space-y-2">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            name="password"
            id="password"
            className="block w-full p-2 border border-black"
            onChange={(e) => setPassword(e.target.value)}
            value={password}
          />
        </div>

        <div className="space-y-2">
          <p>Register as</p>
          <label className="block" htmlFor="student">
            <input
              type="radio"
              selected
              id="student"
              name="registerType"
              onChange={(e) => {
                setRegisterType(e.target.value);
              }}
              value={"student"}
            />{" "}
            Student
          </label>
          <label className="block" htmlFor="instructor">
            <input
              type="radio"
              id="instructor"
              name="registerType"
              onChange={(e) => {
                setRegisterType(e.target.value);
              }}
              value={"instructor"}
            />{" "}
            Instructor
          </label>
        </div>

        {registerType === "student" ? (
          <>
            <div className="space-y-2">
              <label htmlFor="major">Major</label>

              <select
                name="major"
                className="block w-full p-2 border border-black"
                id="major"
                onChange={(e) => setMajor(e.target.value)}
              >
                <option disabled selected></option>
                <option value="Software Engineering">
                  Software Engineering
                </option>
                <option value="Digital Marketing">Digital Marketing</option>
                <option value="Business Management">Business Management</option>
                <option value="IT">IT</option>
              </select>
            </div>

            <div className="space-y-2">
              <label htmlFor="minor">Minor (Optional)</label>

              <select
                name="minor"
                className="block w-full p-2 border border-black"
                id="minor"
                onChange={(e) => setMinor(e.target.value)}
              >
                <option disabled selected></option>
                <option value="Software Engineering">
                  Software Engineering
                </option>
                <option value="Digital Marketing">Digital Marketing</option>
                <option value="Business Management">Business Management</option>
                <option value="IT">IT</option>
              </select>
            </div>
            <div className="space-y-2">
              <label htmlFor="bank">Bank</label>
              <input
                type="text"
                name="bank"
                id="bank"
                className="block w-full p-2 border border-black"
                onChange={(e) => setBank(e.target.value)}
                value={bank}
              />
            </div>
            <div className="space-y-2">
              <label htmlFor="accountNumber">Account number</label>
              <input
                type="text"
                name="accountNumber"
                id="accountNumber"
                className="block w-full p-2 border border-black"
                onChange={(e) => setAccountNumber(e.target.value)}
                value={accountNumber}
              />
            </div>
          </>
        ) : (
          <>
            <div className="space-y-2">
              <label htmlFor="major">Brief Introduction about yourself</label>

              <textarea
                onChange={(e) => {
                  setInstructorDescription(e.target.value);
                }}
                type=""
                rows={6}
                className="w-full"
              />
            </div>
          </>
        )}

        <Button type="submit" text={"Sign Up"} isPrimary={true}></Button>
      </form>
    </section>
  );
};

export default SignUp;
