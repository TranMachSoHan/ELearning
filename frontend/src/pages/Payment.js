import { useState } from "react";
import Button from "../components/Button";
import SectionTitle from "../components/SectionTitle";
import { signup } from "../Utils/APIUltils";
import { useNavigate } from "react-router-dom";

const SignUp = () => {
  const navigate = useNavigate();
  const [bank, setBank] = useState("");
  const [accountNumber, setAccountNumber] = useState("");
  const [name, setName] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    let addPaymentRequest = Object.assign(
      {},
      {
        accountNumber: accountNumber,
        name: name,
        bank: bank,
      }
    );
  };

  return (
    <section className="min-h-[700px] pt-24">
      <SectionTitle title="Payment"></SectionTitle>

      <form onSubmit={handleSubmit} className="w-1/4 pt-20 mx-auto space-y-6">
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
          <label htmlFor="email">Bank</label>
          <input
            type="text"
            name="bank"
            id="bank"
            className="block w-full p-2 border border-black"
            placeholder="ABC Bank"
            onChange={(e) => setBank(e.target.value)}
            value={bank}
          />
        </div>
        <div className="space-y-2">
          <label htmlFor="password">Account number</label>
          <input
            type="number"
            name="accountNumber"
            id="accountNumber"
            className="block w-full p-2 border border-black"
            onChange={(e) => setAccountNumber(e.target.value)}
            value={accountNumber}
          />
        </div>
        <Button type="submit" text={"Confirm"} isPrimary={true}></Button>
      </form>
    </section>
  );
};

export default SignUp;
